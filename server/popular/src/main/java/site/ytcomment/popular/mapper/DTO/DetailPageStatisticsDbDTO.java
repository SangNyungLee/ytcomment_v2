package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class DetailPageStatisticsDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;
    }

    @Getter
    @Builder
    public static class Out{
        private final int idx;
        private final String id;
        private final int channelViewCount;
        private final int channelFavoriteCount;
        private final int channelCommentCount;
        private final int channelLikeCount;
        private final LocalDateTime publishedAt;
    }
}
