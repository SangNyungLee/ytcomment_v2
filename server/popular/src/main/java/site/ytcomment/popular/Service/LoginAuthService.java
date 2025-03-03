package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.LoginAuthServiceDTO;
import site.ytcomment.popular.Util.BcryptUtil;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.DTO.LoginAuthDbDTO;
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
        boolean matchesResult = BcryptUtil.matchesPassword(in.getUserPw(), dbResult.getUserPw());
        System.out.println("비밀번호 비교 결과값 " + matchesResult);
        if (matchesResult){
            return ResponseCode.실패.getCode();
        }else{
            return ResponseCode.성공.getCode();
        }
    }
}
