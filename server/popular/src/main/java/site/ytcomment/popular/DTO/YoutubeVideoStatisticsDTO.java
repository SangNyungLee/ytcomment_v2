package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class YoutubeVideoStatisticsDTO {
    private String id;
    private Long channelViewCount;
    private int channelFavoriteCount;
    private int channelCommentCount;
    private int channelLikeCount;
}
