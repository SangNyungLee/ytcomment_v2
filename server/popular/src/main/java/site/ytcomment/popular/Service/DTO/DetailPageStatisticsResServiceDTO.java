package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DetailPageStatisticsResServiceDTO {
    private String id;
    private int channelViewCount;
    private int channelFavoriteCount;
    private int channelCommentCount;
    private int channelLikeCount;
    private LocalDateTime publishedAt;
}
