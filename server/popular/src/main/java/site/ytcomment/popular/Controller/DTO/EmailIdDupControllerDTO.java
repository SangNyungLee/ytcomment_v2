package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.EmailIdDupServiceDTO;

public class EmailIdDupControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;

        public EmailIdDupServiceDTO.In to(){
            return EmailIdDupServiceDTO.In.builder()
                    .id(this.id)
                    .build();
        }
    }
}
