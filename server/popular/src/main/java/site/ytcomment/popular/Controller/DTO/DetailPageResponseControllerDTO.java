package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DetailPageResponseControllerDTO{
    private int idx;
    private String id;
    private int likeCount;
    private String textOriginal;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String publishedAt;
}
