package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.UpdateUserPwServiceDTO;
import site.ytcomment.popular.Util.BcryptUtil;
import site.ytcomment.popular.common.Enum.QueryResponseCode;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.DTO.UpdateUserPwDbDTO;
import site.ytcomment.popular.mapper.UserInfoMapper;

@Service
@RequiredArgsConstructor
public class UpdateUserPwService {

    private final UserInfoMapper userInfoMapper;
    public UpdateUserPwServiceDTO.Out updateUserPw(UpdateUserPwServiceDTO.In in){
        System.out.println("service In result : " + in.getResult());
        if (in.getResult().equals("0")){
            System.out.println("성공으로 들어옴");
            // 성공했으면 비밀번호 변경
            int mapperResult = userInfoMapper.updateUserPassword(in.to(BcryptUtil.encodePassword(in.getConfirmPassword())));
            System.out.println("매퍼결과" + mapperResult);
            return UpdateUserPwServiceDTO.Out.from(mapperResult);
        } else {
            // 실패했으면 아무것도 하지않음
            System.out.println("실패로 들어왓음");
            return UpdateUserPwServiceDTO.Out.from(0);
        }
    }
}
