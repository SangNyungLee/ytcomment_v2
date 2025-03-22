package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.UpdateUserNameServiceDTO;
import site.ytcomment.popular.Service.UpdateUserNameService;

public class UpdateUserNameControllerDTO {

    @Getter
    @Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    public static class In{
        private final String userName;
        private final String id;

        public UpdateUserNameServiceDTO.In to(String userid){
            return UpdateUserNameServiceDTO.In.builder()
                    .id(userid)
                    .userName(this.userName)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Out{
        private final int result;

        public static UpdateUserNameControllerDTO.Out from(UpdateUserNameServiceDTO.Out out){
            return Out.builder()
                    .result(out.getResult())
                    .build();
        }
    }
}
