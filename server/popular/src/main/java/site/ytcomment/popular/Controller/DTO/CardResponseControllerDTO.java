package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// 카드에 들어가는 값들 담고있는 DTO
public class CardResponseControllerDTO {
    private String id;
    private String thumbnails;
    private String channelTitle;
    private String title;
    private String description;
    private String videoId;
    private String textOriginal;
    private int likeCount;
}
