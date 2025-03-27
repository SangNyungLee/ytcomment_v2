package site.ytcomment.popular.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import site.ytcomment.popular.DTO.YoutubeChannelInfoDTO;
import site.ytcomment.popular.DTO.YoutubeVideoStatisticsDTO;
import site.ytcomment.popular.Service.DTO.SingleVideoSaveServiceDTO;
import site.ytcomment.popular.mapper.GetVideoMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SingleVideoSaveService {

    private final WebClient.Builder webClientBuilder;
    private final GetVideoMapper getVideoMapper;
    private final YoutubeCommentService youtubeCommentService;

    @Value("${youtube.api.key}")
    private String apiKey;
    @Value("${youtube.video.api.url}")
    private String apiUrl;

    public void saveVideoById(SingleVideoSaveServiceDTO.In in) {
        // count 값이 0 일 때만 실행할거임
        System.out.println("서비스 드러옴 " + in.getCount());
        if (in.getCount() == 0) {
            System.out.println("서비스 조건문 안에 들어옴 " + in.getCount());
            System.out.println("서비스 조건문 안에 id값 : " + in.getVideoId());
            try {
                String result = webClientBuilder.baseUrl(apiUrl)
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("key", apiKey)
                                .queryParam("part", "snippet,statistics")
                                .queryParam("id", in.getVideoId())
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                JsonNode item = new ObjectMapper().readTree(result).get("items").get(0); // 단일 영상

                // Snippet → VideoInfo
                LocalDateTime publishedAt = LocalDateTime.parse(
                        item.get("snippet").get("publishedAt").asText(), DateTimeFormatter.ISO_DATE_TIME
                );

                YoutubeChannelInfoDTO videoInfo = YoutubeChannelInfoDTO.builder()
                        .id(in.getVideoId())
                        .channelTitle(item.get("snippet").get("channelTitle").asText())
                        .title(item.get("snippet").get("title").asText())
                        .description(item.get("snippet").get("description").asText())
                        .thumbnails(item.get("snippet").get("thumbnails").get("standard").get("url").asText())
                        .channelId(item.get("snippet").get("channelId").asText())
                        .tags(item.get("snippet").has("tags") ?
                                item.get("snippet").get("tags").elements().next().asText() : " ")
                        .categoryId(item.get("snippet").get("categoryId").asInt())
                        .publishedAt(publishedAt)
                        .build();

                YoutubeVideoStatisticsDTO stats = YoutubeVideoStatisticsDTO.builder()
                        .id(in.getVideoId())
                        .channelViewCount(item.get("statistics").get("viewCount").asLong())
                        .channelFavoriteCount(item.get("statistics").get("favoriteCount").asInt())
                        .channelCommentCount(item.get("statistics").get("commentCount").asInt())
                        .channelLikeCount(item.get("statistics").has("likeCount")
                                ? item.get("statistics").get("likeCount").asInt()
                                : 0)
                        .build();
                System.out.println("저장하는 부분 까지 왔음, singleVideoSaveService부분");
                getVideoMapper.insertVideo(videoInfo);
                getVideoMapper.insertStatistics(stats);

                // 댓글 저장
                youtubeCommentService.searchComment(List.of(in.getVideoId()));

            } catch (Exception e) {
                log.error("단일 영상 저장 실패 videoId: {}", in.getVideoId(), e);
            }
        }
    }
}
