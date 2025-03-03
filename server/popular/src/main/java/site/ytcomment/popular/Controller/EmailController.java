package site.ytcomment.popular.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.EmailDupControllerDTO;
import site.ytcomment.popular.Controller.DTO.EmailSendControllerDTO;
import site.ytcomment.popular.Controller.DTO.EmailSignUpControllerDTO;
import site.ytcomment.popular.Controller.DTO.EmailVerifyAuthControllerDTO;
import site.ytcomment.popular.Service.EmailAuthCodeService;
import site.ytcomment.popular.Service.EmailAuthService;
import site.ytcomment.popular.Service.EmailDupCheckService;
import site.ytcomment.popular.Service.EmailSignUpService;
import site.ytcomment.popular.common.BaseResponse;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Tag(name = "이메일", description = "이메일 API")
public class EmailController {

    private final EmailAuthService emailAuthService;
    private final EmailAuthCodeService emailAuthCodeService;
    private final EmailDupCheckService emailDupCheckService;
    private final EmailSignUpService emailSignUpService;
    // 이메일 중복확인
    @GetMapping("/check-email")
    public ResponseEntity<String> EmailDupCheck(@RequestParam(name = "email") EmailDupControllerDTO.In in){
        String result = emailDupCheckService.checkEmailDup(in.to());
        return ResponseEntity.ok(result);
    }
    // 이메일 중복확인 후 가입
    @PostMapping("/email-signup")
    public ResponseEntity<String> EmailSignUp(@RequestBody EmailSignUpControllerDTO.In in){
        String result = emailSignUpService.SignUpUser(in.to());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/send")
    @Operation(summary = "이메일 전송")
    public BaseResponse sendEmail(@RequestBody EmailSendControllerDTO.In req) {
        String result = emailAuthService.requestEmailAuth(req.to());
        return BaseResponse.success("이메일 전송 성공");
    }

    @PostMapping("/check")
    @Operation(summary = "이메일 인증")
    public BaseResponse checkEmail(@RequestBody EmailVerifyAuthControllerDTO.In in) {
        return emailAuthCodeService.verifyAuthCode(in.to());
    }

}
