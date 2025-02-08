package site.ytcomment.popular.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import site.ytcomment.popular.DTO.YoutubeVideoCommentDTO;
import site.ytcomment.popular.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Slf4j
@Service
public class YoutubeCommentService implements CommentMapper {

    private final WebClient.Builder webClientBuilder;
    private final CommentMapper commentMapper;
    @Override
    public void insertComment(YoutubeVideoCommentDTO youtubeVideoCommentDTO) {
    }

    @Value("${youtube.api.key}")
    private String apiKey;
    @Value("${youtube.comment.api.url}")
    private String apiUrl;

    // 다음 Comment 가져올 때 pageToken 값 필요한데 단순히 저장하는 곳이라 필요없음
    // 나중에 검색기능 쪽에서 구현하면 될듯
    public String searchComment(List<String> videoIdList) {
        for(String videoId : videoIdList) {
            String result = webClientBuilder.baseUrl(apiUrl)
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("key", apiKey)
                            .queryParam("part", "snippet")
                            .queryParam("videoId", videoId)
                            .queryParam("maxResults", 10) // 가져올 댓글 개수
                            .queryParam("order", "relevance")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            try {
                JsonNode data = new ObjectMapper().readTree(result);
                List<YoutubeVideoCommentDTO> commentList = StreamSupport.stream(data.get("items").spliterator(), false)
                        .map(comment ->{
                            String dataStr = comment.get("snippet").get("topLevelComment").get("snippet").get("publishedAt").asText();
                            LocalDateTime localDateTime = LocalDateTime.parse(dataStr, DateTimeFormatter.ISO_DATE_TIME);

                            return YoutubeVideoCommentDTO.builder()
                                    .id(comment.get("snippet").get("videoId").asText())
                                    .likeCount(comment.get("snippet").get("topLevelComment").get("snippet").get("likeCount").asInt())
                                    .textOriginal(comment.get("snippet").get("topLevelComment").get("snippet").get("textOriginal").asText())
                                    .authorDisplayName(comment.get("snippet").get("topLevelComment").get("snippet").get("authorDisplayName").asText())
                                    .authorProfileImageUrl(comment.get("snippet").get("topLevelComment").get("snippet").get("authorProfileImageUrl").asText())
                                    .publishedAt(localDateTime)
                                    .build();
                        }).toList();
                // 댓글 DB에 넣는 로직
                commentList.forEach(commentMapper::insertComment);
            } catch (JsonProcessingException e){
                log.info(e.getMessage());
            }
        }
        return "success";
    }

}
