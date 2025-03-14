package site.ytcomment.popular.Service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailCheckServiceDTO;
import site.ytcomment.popular.Service.DTO.EmailSendServiceDTO;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final EmailSenderService emailSenderService;
    private final EmailAuthCodeService emailAuthCodeService;

    // 이메일 인증 요청 처리
    public String requestEmailAuth(EmailSendServiceDTO.In in){
        // 인증코드 생성
        String authCode = emailAuthCodeService.generateAuthCode();
        EmailCheckServiceDTO.In emailDTO = EmailCheckServiceDTO.In.builder()
                .toMail(in.getEmail())
                .title("회원 가입을 위한 이메일 인증")
                .content("이메일 인증을 위한 절차입니다.<br><br>인증 번호: " + authCode + "<br>해당 번호를 입력하세요.")
                .build();

        // 두개의 서비스가 제대로 이뤄졌는지 확인하는 로직 작성?
        emailSenderService.sendEmail(emailDTO);
        emailAuthCodeService.saveAuthCode(in.getEmail(), authCode);

        return authCode;
    }

    // 인증 번호 검증
//    public BaseResponse verifyAuth(String email, String authCode){
//        return emailAuthCodeService.verifyAuthCode(email, authCode);
//    }
}
