package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class DeleteUserInfoDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;
    }
}
