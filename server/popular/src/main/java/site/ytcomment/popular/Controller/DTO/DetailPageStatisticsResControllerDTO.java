package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailPageStatisticsResControllerDTO {
    private int idx;
    private String id;
    private int channelViewCount;
    private int channelFavoriteCount;
    private int channelCommentCount;
    private int channelLikeCount;
    private String publishedAt;
}
