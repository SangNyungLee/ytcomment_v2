package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.EmailIdDupCheckDbDTO;

public class EmailIdDupServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;

        public EmailIdDupCheckDbDTO.In to(){
            return EmailIdDupCheckDbDTO.In.builder()
                    .id(this.id)
                    .build();
        }
    }
}
