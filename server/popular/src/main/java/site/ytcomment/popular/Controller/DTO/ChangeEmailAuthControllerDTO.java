package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.ChangeEmailAuthServiceDTO;

public class ChangeEmailAuthControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String email;

        public ChangeEmailAuthServiceDTO.In to(){
            return ChangeEmailAuthServiceDTO.In
                    .builder()
                    .email(this.email)
                    .build();
        }
    }
}
