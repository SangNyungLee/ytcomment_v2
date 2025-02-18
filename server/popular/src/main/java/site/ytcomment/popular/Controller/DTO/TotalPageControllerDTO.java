package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.TotalPageServiceDTO;

public class TotalPageControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In {
        private final int categoryId;

        public TotalPageServiceDTO.In to() {
            return TotalPageServiceDTO.In.builder()
                    .categoryId(this.categoryId)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{

        private final int totalPage;

        public static TotalPageControllerDTO.Out from(TotalPageServiceDTO.Out out) {
            return TotalPageControllerDTO.Out
                    .builder()
                    .totalPage(out.getTotalPage())
                    .build();
        }
    }
}
