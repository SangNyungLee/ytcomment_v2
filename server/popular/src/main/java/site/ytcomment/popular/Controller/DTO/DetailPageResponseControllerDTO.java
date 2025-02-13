package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DetailPageResponseControllerDTO{
    private int id;
    private String videoId; // 나중에 id로 바껴야됨 얜
    private int likeCount;
    private String textOriginal;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String publishedAt;
}
