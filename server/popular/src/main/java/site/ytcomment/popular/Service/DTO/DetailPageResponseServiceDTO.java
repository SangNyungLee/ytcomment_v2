package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import site.ytcomment.popular.DTO.YoutubeVideoCommentDTO;

import java.time.LocalDateTime;

@Getter
@Builder
public class DetailPageResponseServiceDTO {
    private int id;
    private String videoId; // 나중에 id로 바껴야댐
    private int likeCount;
    private String textOriginal;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private LocalDateTime publishedAt;
}
