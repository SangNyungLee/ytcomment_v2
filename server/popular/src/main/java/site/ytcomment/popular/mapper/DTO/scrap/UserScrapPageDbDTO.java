package site.ytcomment.popular.mapper.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class UserScrapPageDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
    }

    @Getter
    @Builder
    public static class Out{
        private final String id;
        private final String title;
        private final String channelTitle;
        private final String thumbnails;
        private final int categoryId;
        private final int channelViewCount;
        private final int channelLikeCount;
        private final int channelCommentCount;
        private final LocalDateTime publishedAt;
    }
}
