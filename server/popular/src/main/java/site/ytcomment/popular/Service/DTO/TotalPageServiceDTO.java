package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.TotalPageDbDTO;

public class TotalPageServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final int categoryId;

        public TotalPageDbDTO.In to(){
            return TotalPageDbDTO.In
                    .builder()
                    .categoryId(this.categoryId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int totalPage;

        public static TotalPageServiceDTO.Out from(TotalPageDbDTO.Out out){
            return Out.builder()
                    .totalPage(out.getTotalPage())
                    .build();
        }
    }
}
