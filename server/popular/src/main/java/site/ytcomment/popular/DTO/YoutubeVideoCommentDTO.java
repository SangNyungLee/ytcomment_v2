package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class YoutubeVideoCommentDTO {
    private String id;
    private int likeCount;
    private String textOriginal;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private LocalDateTime publishedAt;
}
