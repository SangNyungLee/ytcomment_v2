package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.SingleVideoSaveServiceDTO;

public class SingleVideoSaveControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final int count;
        private final String videoId;

        public static SingleVideoSaveServiceDTO.In to(int count, String videoId) {
            return SingleVideoSaveServiceDTO.In.builder()
                    .count(count)
                    .videoId(videoId)
                    .build();
        }
    }
}
