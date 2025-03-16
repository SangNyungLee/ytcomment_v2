package site.ytcomment.popular.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class DetailPageTagsDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String videoId;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final String tags;
    }
}
