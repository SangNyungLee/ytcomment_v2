package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class DetailPageCommentDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int idx;
        private final String id;
        private final int likeCount;
        private final String textOriginal;
        private final String authorDisplayName;
        private final String authorProfileImageUrl;
        private final LocalDateTime publishedAt;
    }
}
