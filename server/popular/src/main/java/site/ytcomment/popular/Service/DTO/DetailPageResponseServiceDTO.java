package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.YoutubeVideoCommentDTO;

import java.time.LocalDateTime;

@Getter
@Builder
public class DetailPageResponseServiceDTO {
    private int idx;
    private String id;
    private int likeCount;
    private String textOriginal;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private LocalDateTime publishedAt;
}
