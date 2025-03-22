package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.UpdateUserNameDbDTO;

public class UpdateUserNameServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String id;
        private final String userName;

        public UpdateUserNameDbDTO.In to(){
            return UpdateUserNameDbDTO.In.builder()
                    .id(this.id)
                    .userName(this.userName)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;

        public static UpdateUserNameServiceDTO.Out from(int result){
            return Out.builder()
                    .result(result)
                    .build();
        }
    }
}
