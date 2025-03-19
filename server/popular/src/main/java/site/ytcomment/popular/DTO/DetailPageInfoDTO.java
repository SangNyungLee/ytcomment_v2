package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class DetailPageInfoDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force=true)
    @RequiredArgsConstructor
    public static class In{
        private final String videoId;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int idx;
        private final String id;
        private final String thumbnails;
        private final String channelTitle;
        private final String title;
        private final String description;
        private final String channelId;
        private final String tags;
        private final int categoryId;
        private final LocalDateTime publishedAt;
        private final LocalDateTime createdAt;
        private final int channelLikeCount;
    }
}
