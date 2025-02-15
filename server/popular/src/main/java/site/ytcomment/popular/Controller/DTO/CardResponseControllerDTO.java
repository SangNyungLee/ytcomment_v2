package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// 카드에 들어가는 값들 담고있는 DTO
public class CardResponseControllerDTO {
    private int idx;
    private String thumbnails;
    private String channelTitle;
    private String title;
    private String description;
    private String id;
    private String textOriginal;
    private String channelId;
    private int likeCount;
}
