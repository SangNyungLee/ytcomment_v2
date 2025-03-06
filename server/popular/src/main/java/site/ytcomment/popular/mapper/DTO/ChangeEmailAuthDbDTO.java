package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.ChangeEmailAuthServiceDTO;

public class ChangeEmailAuthDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String email;
    }
}
