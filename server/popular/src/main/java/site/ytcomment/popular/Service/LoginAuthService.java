package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import site.ytcomment.popular.Service.DTO.LoginAuthServiceDTO;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
import site.ytcomment.popular.Util.BcryptUtil;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.DTO.LoginAuthDbDTO;
import site.ytcomment.popular.mapper.DTO.UserInfoDbDTO;
import site.ytcomment.popular.mapper.LoginAuthMapper;

@Service
@RequiredArgsConstructor
public class LoginAuthService {

    private final LoginAuthMapper loginAuthMapper;

    public LoginAuthServiceDTO.Out getUserPw(LoginAuthServiceDTO.In in){
        /*
        * 실패 : 0,
        * 성공 : 1,
        * 인증실패 : 2
        * */
        LoginAuthDbDTO.Out dbResult = loginAuthMapper.selectUserPw(in.to());
        LoginAuthServiceDTO.Out serviceDTO = LoginAuthServiceDTO.Out.from(dbResult);
        if (serviceDTO == null){
            return null;
        }
        boolean matchesResult = BcryptUtil.matchesPassword(in.getUserPw(), serviceDTO.getUserPw());
        if (matchesResult){
            return serviceDTO;
        } else {
            return null;
        }
    }
        // 이메일로 사용자 ID 가져오기
    public UserInfoServiceDTO.Out getUserEmailById(UserInfoServiceDTO.In in){
        UserInfoDbDTO.Out serviceResult = loginAuthMapper.selectUserEmailById(in.getUserId());
        return UserInfoServiceDTO.Out.from(serviceResult);
    }

}
