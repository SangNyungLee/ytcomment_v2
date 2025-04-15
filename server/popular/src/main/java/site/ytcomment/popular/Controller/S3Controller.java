package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.UserInfoControllerDTO;
import site.ytcomment.popular.Controller.DTO.myPage.UpdateProfileImgControllerDTO;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
import site.ytcomment.popular.Service.GetUserInfoService;
import site.ytcomment.popular.Service.S3UploadService;
import site.ytcomment.popular.config.jwt.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3UploadService s3UploadService;
    private final GetUserInfoService getUserInfoService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @ModelAttribute UpdateProfileImgControllerDTO.In in) {
        try {
            // 아이디를 통해 userinfo에 있는 이미지 값을 가져와야됨
            UserInfoServiceDTO.Out userinfo = getUserInfoService.fetchUserInfo(UserInfoControllerDTO.In.to(userDetails.getUserId()));
            // 불러온 이미지의 key값을 추출
            //url 생성
            String url = s3UploadService.uploadImage(in.to(userDetails.getUserId()));
            // url 생성한거 bucket update
            // url DB 업데이트
            return ResponseEntity.ok().body(url);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("업로드 실패: " + e.getMessage());
        }
    }
}
