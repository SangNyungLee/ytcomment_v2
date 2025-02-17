package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CardDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final int page;
        private final int newCategory;
        private final int startIndex;
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
        private final String textOriginal;
        private final String channelId;
        private final int likeCount;

    }
}
