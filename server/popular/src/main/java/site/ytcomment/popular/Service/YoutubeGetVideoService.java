package site.ytcomment.popular.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import site.ytcomment.popular.DTO.YoutubeChannelInfoDTO;
import site.ytcomment.popular.DTO.YoutubeVideoStatisticsDTO;
import site.ytcomment.popular.mapper.GetVideoMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeGetVideoService{

    private final WebClient.Builder webClientBuilder;
    private final GetVideoMapper getVideoMapper;
    private final YoutubeCommentService youtubeCommentService;

    @Value("${youtube.api.key}")
    private String apiKey;
    @Value("${youtube.video.api.url}")
    private String apiUrl;


    public String searchVideos() {
        List<Integer> categoryIds = List.of(0, 10, 15, 20);

        /// 각 카테고리 별로 데이터 가져오기
        for(Integer categoryId : categoryIds) {
            String result = webClientBuilder.baseUrl(apiUrl)
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("key", apiKey)
                            .queryParam("part", "snippet, statistics")
                            .queryParam("chart", "mostPopular")
                            .queryParam("maxResults", 50)
                            .queryParam("videoCategoryId", categoryId)
                            .queryParam("regionCode", "KR")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            try {
                // TODO 2025.02.18 sanglee JsonNode 말고 ObjectMapper를 DTO로 받을 수 있는 방법 있음(아마도) 추후수정할 것
                JsonNode data = new ObjectMapper().readTree(result);
                List<String> videoIdList = new ArrayList<>();
                /// JsonNode -> stream으로 변경하기 위해서 StreamSupport.stream함수 사용, spliterator는 stream짤라주는 함수임
                List<YoutubeChannelInfoDTO> videoList = StreamSupport.stream(data.get("items").spliterator(), false)
                        .map(item -> {
                            /// 시간 변환 "publishedAt": "2025-02-03T09:00:06Z"
                            String dateStr = item.get("snippet").get("publishedAt").asText();
                            videoIdList.add(item.get("id").asText());
                            LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);

                            return YoutubeChannelInfoDTO.builder()
                                    .id(item.get("id").asText())
                                    .channelTitle(item.get("snippet").get("channelTitle").asText())
                                    .title(item.get("snippet").get("title").asText())
                                    .description(item.get("snippet").get("description").asText())
                                    .thumbnails(item.get("snippet").get("thumbnails").get("standard").get("url").asText())
                                    .channelId(item.get("snippet").get("channelId").asText())
                                    /// tags는 배열로 되어 있어서 split 해줌
                                    .tags(item.get("snippet").has("tags") ?
                                            StreamSupport.stream(item.get("snippet").get("tags").spliterator(), false)
                                                    .map(JsonNode::asText)
                                                    .collect(Collectors.joining(",")) : " ")
                                    .categoryId(item.get("snippet").get("categoryId").asInt())
                                    .publishedAt(localDateTime)
                                    .build();
                        })
                        .toList();
                /// 통계값 넣기
                List<YoutubeVideoStatisticsDTO> statistics = StreamSupport.stream(data.get("items").spliterator(), false)
                        .map(item ->{
                            return YoutubeVideoStatisticsDTO.builder()
                                    .id(item.get("id").asText())
                                    .channelViewCount(item.get("statistics").get("viewCount").asLong())
                                    .channelFavoriteCount(item.get("statistics").get("favoriteCount").asInt())
                                    .channelCommentCount(item.get("statistics").get("commentCount").asInt())
//                                    .channelLikeCount(item.get("statistics").get("likeCount").asInt())
                                    .channelLikeCount(Optional.ofNullable(item.get("statistics").get("likeCount"))
                                            .map(JsonNode::asInt).orElse(0))
                                    .build();
                        }).toList();

                /// forEach(video -> youtubeUserMapper.insertVideo(video)) 람다식표현
                videoList.forEach(getVideoMapper::insertVideo); // Video 데이터 넣기
                statistics.forEach(getVideoMapper::insertStatistics); // statistics 데이터 넣기
                System.out.println(youtubeCommentService.searchComment(videoIdList)); // commentService불러와서 videoId값 별로 Comment값 가져오기
            }
            catch (JsonProcessingException e){
                log.warn("메시지 오류 ", e);
                return null;
            } catch (DateTimeParseException e){
                ///  데이터타입 파싱처리 오류
                return null;
            }
        }
        return "success";
    }
}
