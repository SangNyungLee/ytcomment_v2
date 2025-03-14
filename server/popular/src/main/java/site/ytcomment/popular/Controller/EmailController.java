package site.ytcomment.popular.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.*;
import site.ytcomment.popular.Service.*;
import site.ytcomment.popular.Service.email.*;
import site.ytcomment.popular.common.BaseResponse;
import site.ytcomment.popular.common.Enum.ResponseCode;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Tag(name = "이메일", description = "이메일 API")
public class EmailController {

    private final EmailAuthService emailAuthService;
    private final EmailAuthCodeService emailAuthCodeService;
    private final EmailDupCheckService emailDupCheckService;
    private final EmailSignUpService emailSignUpService;
    private final EmailIdDupCheckService emailIdDupCheckService;
    private final CheckEmailAuthService checkEmailAuthService;
    // 이메일 중복확인
    @GetMapping("/check-email")
    public ResponseEntity<String> EmailDupCheck(@RequestParam(name = "email") EmailDupControllerDTO.In in){
        String result = emailDupCheckService.checkEmailDup(in.to());
        return ResponseEntity.ok(result);
    }
    // 아이디 중복확인
    @GetMapping("/check-id")
    public ResponseEntity<String> EmailIdDupCheck(@RequestParam(name = "id") EmailIdDupControllerDTO.In in){
        Integer result = emailIdDupCheckService.EmailIdDupCheck(in.to());
        if (result.equals(0))
            return ResponseEntity.ok(ResponseCode.성공.getCode());
        else
            return ResponseEntity.ok(ResponseCode.실패.getCode());

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
        System.out.println("이메일 보내기");
        return BaseResponse.success("이메일 전송 성공");
    }

    @PostMapping("/check")
    @Operation(summary = "이메일 인증")
    public BaseResponse checkEmail(@RequestBody EmailVerifyAuthControllerDTO.In in) {
        BaseResponse result = emailAuthCodeService.verifyAuthCode(in.to());
        System.out.println("!!" + result.getStatusCode());
        // 0 : 성공, 1 : 실패
        if (result.getMessage().equals(0)){
            //성공햇을 때 그 아이디의 auth를 true로 바꾸기
            ChangeEmailAuthControllerDTO.In authDTO = ChangeEmailAuthControllerDTO.In.builder()
                    .email(in.getEmail()).build();
            checkEmailAuthService.updateUserAuth(authDTO.to());
        }
        System.out.println("?? " + result.getMessage());
        return result;
    }

}
