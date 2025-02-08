package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class YoutubeVideoCommentDTO {
    private String videoId;
    private int likeCount;
    private String textOriginal;
    private String authorName;
    private String authorProfileImageUrl;
    private LocalDateTime publishedAt;
}
