package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.UserInfoServiceDTO;
import site.ytcomment.popular.mapper.DTO.UserInfoDbDTO;
import site.ytcomment.popular.mapper.UserInfoMapper;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

@Service
@RequiredArgsConstructor
public class GetUserInfoService {
    private final UserInfoMapper userInfoMapper;
    public UserInfoServiceDTO.Out fetchUserInfo(UserInfoServiceDTO.In in) {
        UserInfoDbDTO.Out dbResult = userInfoMapper.selectUserInfo(in.to());
        return UserInfoServiceDTO.Out.from(dbResult);
    }
}
