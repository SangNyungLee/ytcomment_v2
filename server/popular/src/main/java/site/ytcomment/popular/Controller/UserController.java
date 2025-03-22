package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Controller.DTO.DeleteUserInfoControllerDTO;
import site.ytcomment.popular.Service.DeleteUserService;
import site.ytcomment.popular.config.jwt.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final DeleteUserService deleteUserService;

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal CustomUserDetails userDetails){
        deleteUserService.deleteUserByEmail(DeleteUserInfoControllerDTO.In.to(userDetails.getUserId()));
        return ResponseEntity.ok("회원탈퇴 완료");
    }
}
