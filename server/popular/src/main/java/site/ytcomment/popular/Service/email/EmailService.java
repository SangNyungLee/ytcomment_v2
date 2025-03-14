package site.ytcomment.popular.Service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailCheckServiceDTO;
import site.ytcomment.popular.common.BaseResponse;
import site.ytcomment.popular.config.RedisConfig;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final RedisConfig redisConfig;
    private int authNumber;

    // 이메일 인증에 관한 정보
    @Value("${spring.mail.username}")
    private String serviceName;

    // 랜덤한 인증번호 생성
    public void makeRandomNum(){
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0 ; i < 6 ; i++){
            randomNumber += Integer.toString(r.nextInt(10));
        }
        authNumber = Integer.parseInt(randomNumber);
    }

    // 이메일 전송
    public void mailSend(EmailCheckServiceDTO.In emailCheckServiceDTO){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(emailCheckServiceDTO.getSetForm()); // 서비스 이름
            helper.setTo(emailCheckServiceDTO.getToMail()); // Client 이메일 주소
            helper.setSubject(emailCheckServiceDTO.getTitle()); // 이메일 제목
            helper.setText(emailCheckServiceDTO.getContent(), true); // content, html : true
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // 에러 출력
        }

        // Redis에 3분 동안 이메일과 인증 코드 저장
        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        valueOperations.set(emailCheckServiceDTO.getToMail(), Integer.toString(authNumber), 180, TimeUnit.SECONDS);
    }

    // 이메일 작성
    public String joinEmail(String email){
        makeRandomNum();
        EmailCheckServiceDTO.In emailCheckServiceDTO = EmailCheckServiceDTO.In.builder()
                .setForm(serviceName)
                .toMail(email)
                .title("회원 가입을 위한 이메일 입니다.")
                .content("이메일을 인증하기 위한 절차 입니다." +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "회원 가입 폼에 해당하는 번호를 입력해주세요.")
                .build();
        mailSend(emailCheckServiceDTO);
        return Integer.toString(authNumber);
    }

    public BaseResponse checkAuthNumber(String email, String authNum){
        ValueOperations<String, String> valueOperations = redisConfig.redisTemplate().opsForValue();
        String code = valueOperations.get(email);
        if (Objects.equals(code, authNum)){
            return BaseResponse.success("인증 성공");
        } else{
            return BaseResponse.fail(400, "인증 실패");
        }
    }

}
