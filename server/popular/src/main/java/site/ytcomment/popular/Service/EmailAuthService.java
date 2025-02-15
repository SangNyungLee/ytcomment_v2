package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailCheckReqServiceDTO;
import site.ytcomment.popular.common.BaseResponse;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final EmailSenderService emailSenderService;
    private final EmailAuthCodeService emailAuthCodeService;

    // 이메일 인증 요청 처리
    public String requestEmailAuth(String email){
        String authCode = emailAuthCodeService.generateAuthCode();
        EmailCheckReqServiceDTO emailDTO = EmailCheckReqServiceDTO.builder()
                .toMail(email)
                .title("회원 가입을 위한 이메일 인증")
                .content("이메일 인증을 위한 절차입니다.<br><br>인증 번호: " + authCode + "<br>해당 번호를 입력하세요.")
                .build();

        emailSenderService.sendEmail(emailDTO);
        emailAuthCodeService.saveAuthCode(email, authCode);

        return authCode;
    }

    // 인증 번호 검증
    public BaseResponse verifyAuth(String email, String authCode){
        return emailAuthCodeService.verifyAuthCode(email, authCode);
    }
}
