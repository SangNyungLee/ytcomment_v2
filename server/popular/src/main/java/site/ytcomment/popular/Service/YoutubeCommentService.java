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
    /*
     * 해결할일
     * 1. Topic 영상 같은 경우 Comment 값들을 제공해주지 않는데 이걸 해결할 방법 찾기
     * */
    public String searchComment(List<String> videoIdList) {
        try {
            for (String videoId : videoIdList) {
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
                    // 가끔 비어있는 값들이 오는 경우가 있어서 빈 값이면 그냥 continue 해버림
                    // Youtube 영상 중에서 Topic은 댓글 접근이 불가능해서 요청을 보내면 403 return을 해주는데
                    // 이거 어떻게 처리해야할지 잘 모르겠어서 그냥 error메시지 날라오면 continue해버리게 바꿔버렸음
                    if(data.get("items") == null || data.get("items").isEmpty() || data.has("error")){
                        log.warn("댓글이 없는 영상 : {}", videoId);
                        continue;
                    }
                    List<YoutubeVideoCommentDTO> commentList = StreamSupport.stream(data.get("items").spliterator(), false)
                            .map(comment -> {
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
                } catch (JsonProcessingException e) {
                    log.info("실행 실패한 JSON video : {}", videoId, e);
                } catch (Exception e) {
                    log.info("실패한 값 : {}", videoId, e);
                }
            }
        } catch (Exception e){
            log.warn(e.getMessage());
        }
        return "success";
    }

}
