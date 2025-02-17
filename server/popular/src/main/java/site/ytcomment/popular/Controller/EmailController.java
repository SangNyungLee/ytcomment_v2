package site.ytcomment.popular.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Controller.DTO.EmailCheckReqControllerDTO;
import site.ytcomment.popular.Controller.DTO.EmailSendReqControllerDTO;
import site.ytcomment.popular.Service.EmailAuthCodeService;
import site.ytcomment.popular.Service.EmailAuthService;
import site.ytcomment.popular.Service.EmailSenderService;
import site.ytcomment.popular.Service.EmailService;
import site.ytcomment.popular.common.BaseResponse;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@Tag(name = "이메일", description = "이메일 API")
public class EmailController {

    private final EmailService emailService;
    private final EmailAuthService emailAuthService;
    private final EmailAuthCodeService emailAuthCodeService;
    private final EmailSenderService emailSenderService;

    @PostMapping("/send")
    @Operation(summary = "이메일 전송")
//    public BaseResponse sendEmail(@RequestBody EmailSendReqControllerDTO request) {
//        emailService.joinEmail(request.getEmail());
//        return BaseResponse.success("이메일 전송 성공");
//    }
    public BaseResponse sendEmail(@RequestBody EmailSendReqControllerDTO req) {
        emailAuthService.requestEmailAuth(req.getEmail());
        return BaseResponse.success("이메일 전송 성공");
    }

    @PostMapping("/check")
    @Operation(summary = "이메일 인증")
//    public BaseResponse checkEmail(@RequestBody EmailCheckReqControllerDTO request) {
//        return emailService.checkAuthNumber(request.getEmail(), request.getAuthNum());
//    }
    public BaseResponse checkEmail(@RequestBody EmailCheckReqControllerDTO req) {
        return emailAuthCodeService.verifyAuthCode(req.getEmail(), req.getAuthNum());
    }

}
