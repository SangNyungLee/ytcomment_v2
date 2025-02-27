package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.EmailSendServiceDTO;

public class EmailSendControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String email;

        public EmailSendServiceDTO.In to(){
            return EmailSendServiceDTO.In
                    .builder()
                    .email(this.email)
                    .build();
        }
    }
}
