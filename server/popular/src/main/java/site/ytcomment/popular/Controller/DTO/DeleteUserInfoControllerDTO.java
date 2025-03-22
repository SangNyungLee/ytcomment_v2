package site.ytcomment.popular.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.Service.DTO.DeleteUserInfoServiceDTO;

public class DeleteUserInfoControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;

        public static DeleteUserInfoServiceDTO.In to(String userId){
            return DeleteUserInfoServiceDTO.In.builder()
                    .userId(userId)
                    .build();
        }
    }
}
