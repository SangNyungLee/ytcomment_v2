package site.ytcomment.popular.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseServiceDTO {
    private String id; // 이거 나중에 String으로 바꿔야됨
    private String thumbnails;
    private String channelTitle;
    private String title;
    private String description;
    private String videoId;
    private String textOriginal;
    private int likeCount;
}
