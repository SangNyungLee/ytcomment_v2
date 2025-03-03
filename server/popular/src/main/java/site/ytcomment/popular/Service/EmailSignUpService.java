package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Enum.SocialEnum;
import site.ytcomment.popular.Service.DTO.EmailSignUpServiceDTO;
import site.ytcomment.popular.Util.BcryptUtil;
import site.ytcomment.popular.mapper.EmailSignUpMapper;

@Service
@RequiredArgsConstructor
public class EmailSignUpService {

    private final EmailSignUpMapper emailSignUpMapper;

    public String SignUpUser(EmailSignUpServiceDTO.In in){
        emailSignUpMapper.insertUserInfo(in.to(BcryptUtil.encodePassword(in.getUserPw())));
//        SocialEnum 소셜 = SocialEnum.이메일.getEnum(in.getSocial());
//        if(소셜.is카카오()){
//
//        }
//
//        String 소셜코드 =
//                switch (소셜){
//                    case 이메일 -> "";
//                    case 카카오 -> "";
//                    case 카카오 -> "";
//                    case 카카오 -> "";
//                    case 카카오 -> "";
//                    default -> "";
//                };
        return "success";
    }
}
