package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.ChangeEmailAuthDbDTO;

public class ChangeEmailAuthServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String email;

        public ChangeEmailAuthDbDTO.In to(){
            return ChangeEmailAuthDbDTO.In
                    .builder()
                    .email(this.email)
                    .build();
        }
    }
}
