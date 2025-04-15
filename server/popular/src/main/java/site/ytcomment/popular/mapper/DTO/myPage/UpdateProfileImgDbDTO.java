package site.ytcomment.popular.mapper.DTO.myPage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UpdateProfileImgDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final String userId;
    }
}
