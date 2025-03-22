package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.UpdateUserNameControllerDTO;
import site.ytcomment.popular.Controller.DTO.UserInfoControllerDTO;
import site.ytcomment.popular.Service.DTO.UpdateUserNameServiceDTO;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
import site.ytcomment.popular.Service.GetUserInfoService;
import site.ytcomment.popular.Service.UpdateUserNameService;
import site.ytcomment.popular.config.jwt.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {
    private final GetUserInfoService getUserInfoService;
    private final UpdateUserNameService updateUserNameService;
    @GetMapping("/getUserInfo")
    public UserInfoControllerDTO.Out getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserInfoServiceDTO.Out serviceResult = getUserInfoService.fetchUserInfo(UserInfoControllerDTO.In.to(userDetails.getUserId()));
        return UserInfoControllerDTO.Out.from(serviceResult);
    }

    @PostMapping("/updateUserName")
    public UpdateUserNameControllerDTO.Out updateUserName(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @RequestBody UpdateUserNameControllerDTO.In in) {
        UpdateUserNameServiceDTO.Out serviceResult = updateUserNameService.updateUserName(in.to(userDetails.getUserId()));
        return UpdateUserNameControllerDTO.Out.from(serviceResult);
    }
}
