package site.ytcomment.popular.Service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.ytcomment.popular.mapper.DTO.DeleteUserInfoDbDTO;

public class DeleteUserInfoServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final String userId;

        public DeleteUserInfoDbDTO.In to(){
            return DeleteUserInfoDbDTO.In.builder()
                    .userId(this.userId)
                    .build();
        }
    }
}
