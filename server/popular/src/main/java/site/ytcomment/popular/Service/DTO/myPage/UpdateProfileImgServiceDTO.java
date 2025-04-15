package site.ytcomment.popular.Service.DTO.myPage;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class UpdateProfileImgServiceDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class In {
        private final MultipartFile file;
        private final String userId;
    }
}
