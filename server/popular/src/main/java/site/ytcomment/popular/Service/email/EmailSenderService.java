package site.ytcomment.popular.Service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailCheckServiceDTO;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String serviceName;

    // 이메일 전송기능
    public void sendEmail(EmailCheckServiceDTO.In in) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(serviceName);
            helper.setTo(in.getToMail());
            helper.setSubject(in.getTitle());
            helper.setText(in.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch(MessagingException e){
            e.printStackTrace(); // 로깅 추가하면 좋음
        }
    }
}
