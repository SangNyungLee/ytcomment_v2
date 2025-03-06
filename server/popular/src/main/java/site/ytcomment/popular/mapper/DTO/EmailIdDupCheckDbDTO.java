package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EmailIdDupCheckDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;
    }
}
