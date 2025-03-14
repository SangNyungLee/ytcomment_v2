package site.ytcomment.popular.mapper.DTO.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserVideoLikeDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
        private final String videoId;
    }
}
