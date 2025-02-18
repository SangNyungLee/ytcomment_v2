package site.ytcomment.popular.mapper.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TotalPageDbDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final int categoryId;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int totalPage;
    }
}
