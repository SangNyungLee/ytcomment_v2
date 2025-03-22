package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UpdateUserPwDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String encryptConfirmPassword;
        private final String userId;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;
    }
}
