package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailSignUpServiceDTO;
import site.ytcomment.popular.Util.BcryptUtil;
import site.ytcomment.popular.mapper.EmailSignUpMapper;

@Service
@RequiredArgsConstructor
public class EmailSignUpService {

    private final EmailSignUpMapper emailSignUpMapper;

    public String SignUpUser(EmailSignUpServiceDTO.In in){
        emailSignUpMapper.insertUserInfo(in.to(BcryptUtil.encodePassword(in.getUserPw())));
        return "success";
    }
}
