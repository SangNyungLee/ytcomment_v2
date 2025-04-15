package site.ytcomment.popular.Controller.DTO.myPage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import site.ytcomment.popular.Service.DTO.myPage.UpdateProfileImgServiceDTO;

public class UpdateProfileImgControllerDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In{
        private final MultipartFile file;

        public UpdateProfileImgServiceDTO.In to(String userId){
            return UpdateProfileImgServiceDTO.In.builder()
                    .file(this.file)
                    .userId(userId)
                    .build();
        }
    }
}
