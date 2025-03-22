package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UpdateUserNameDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;
        private final String userName;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;
    }
}
