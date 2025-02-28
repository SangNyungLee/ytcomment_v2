package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailSignUpServiceDTO;
import site.ytcomment.popular.mapper.EmailSignUpMapper;

@Service
@RequiredArgsConstructor
public class EmailSignUpService {

    private final EmailSignUpMapper emailSignUpMapper;
    private final PasswordEncoder passwordEncoder;

    public String SignUpUser(EmailSignUpServiceDTO.In in){
        String hashedPassword = passwordEncoder.encode(in.getUserPw());
        emailSignUpMapper.insertUserInfo(in.to(hashedPassword));
        return "success";
    }
}
