package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class SingleVideoSaveServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final int count;
        private final String videoId;
    }
}
