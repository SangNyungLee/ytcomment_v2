package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public String getUserPw(LoginAuthServiceDTO.In in){
        /*
        1. 클라이언트 비밀번호 암호화 후 서버에 있는 비밀번호 가져와서 서로 비교
         */
        LoginAuthDbDTO.Out dbResult = loginAuthMapper.selectUserPw(in.to());
        if (dbResult == null){
            return ResponseCode.실패.getCode();
        }
        boolean matchesResult = BcryptUtil.matchesPassword(in.getUserPw(), dbResult.getUserPw());

        if (matchesResult){
            if (dbResult.getUserAuth().equals(0)){
                return ResponseCode.인증없음.getCode();
            } else {
                return ResponseCode.성공.getCode();
            }
        } else {
            return ResponseCode.실패.getCode();
        }
    }
        // 이메일로 사용자 ID 가져오기
    public UserInfoServiceDTO.Out getUserEmailById(UserInfoServiceDTO.In in){
        UserInfoDbDTO.Out serviceResult = loginAuthMapper.selectUserEmailById(in.getUserId());
        return UserInfoServiceDTO.Out.from(serviceResult);
    }

}
