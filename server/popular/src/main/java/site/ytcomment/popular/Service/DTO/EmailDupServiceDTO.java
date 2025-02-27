package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.EmailDupDbDTO;

public class EmailDupServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String email;

        public EmailDupDbDTO.In to(){
            return EmailDupDbDTO.In
                    .builder()
                    .email(this.email)
                    .build();
        }
    }
}
