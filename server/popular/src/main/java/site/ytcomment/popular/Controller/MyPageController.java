package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.LoginAuthControllerDTO;
import site.ytcomment.popular.Controller.DTO.UpdateUserNameControllerDTO;
import site.ytcomment.popular.Controller.DTO.UpdateUserPwControllerDTO;
import site.ytcomment.popular.Controller.DTO.UserInfoControllerDTO;
import site.ytcomment.popular.Service.DTO.UpdateUserNameServiceDTO;
import site.ytcomment.popular.Service.DTO.UpdateUserPwServiceDTO;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
import site.ytcomment.popular.Service.GetUserInfoService;
import site.ytcomment.popular.Service.LoginAuthService;
import site.ytcomment.popular.Service.UpdateUserNameService;
import site.ytcomment.popular.Service.UpdateUserPwService;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.config.jwt.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {
    private final GetUserInfoService getUserInfoService;
    private final UpdateUserNameService updateUserNameService;
    private final UpdateUserPwService updateUserPwService;
    private final LoginAuthService loginAuthService;

    @GetMapping("/getUserInfo")
    public UserInfoControllerDTO.Out getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserInfoServiceDTO.Out serviceResult = getUserInfoService.fetchUserInfo(UserInfoControllerDTO.In.to(userDetails.getUserId()));
        return UserInfoControllerDTO.Out.from(serviceResult);
    }

    @GetMapping("/getUserLikeInfo")
    public UserInfoControllerDTO.Out getUserLikeCount(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserInfoServiceDTO.Out serviceResult = getUserInfoService.fetchUserInfo(UserInfoControllerDTO.In.to(userDetails.getUserId()));
        return UserInfoControllerDTO.Out.fromUserLikeCount(serviceResult);
    }

    @PostMapping("/updateUserName")
    public UpdateUserNameControllerDTO.Out updateUserName(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @RequestBody UpdateUserNameControllerDTO.In in) {
        UpdateUserNameServiceDTO.Out serviceResult = updateUserNameService.updateUserName(in.to(userDetails.getUserId()));
        return UpdateUserNameControllerDTO.Out.from(serviceResult);
    }

    // 마이페이지에서 비밀번호 수정하는 api
    @PostMapping("/updateUserPw")
    public ResponseEntity<?> updateUserPw(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @RequestBody UpdateUserPwControllerDTO.In in) {
        // 기존 비밀번호랑 같은지 비교하고 비밀번호가 같다면 새로운 비밀번호 암호화 한 후 DB에 저장
        // 1. 비밀번호가 기존 비밀번호와 동일하면
        String checkResult = loginAuthService.getUserPw(in.toLoginAuth(userDetails.getUserId()));
        System.out.println("checkResult = " + checkResult);
        UpdateUserPwServiceDTO.Out serviceResult =
                updateUserPwService.updateUserPw(in.to(checkResult, userDetails.getUserId()));
        return ResponseEntity.ok(serviceResult);
    }
}
