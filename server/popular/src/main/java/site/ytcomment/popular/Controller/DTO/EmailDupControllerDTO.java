package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.EmailDupServiceDTO;

public class EmailDupControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String email;

        public EmailDupServiceDTO.In to(){
            return EmailDupServiceDTO.In
                    .builder()
                    .email(this.email)
                    .build();
        }
    }
}
