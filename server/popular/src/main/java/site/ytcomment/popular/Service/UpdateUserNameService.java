package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.UpdateUserNameServiceDTO;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.DTO.UpdateUserNameDbDTO;
import site.ytcomment.popular.mapper.UserInfoMapper;

@Service
@RequiredArgsConstructor
public class UpdateUserNameService {
    private final UserInfoMapper userInfoMapper;

    public UpdateUserNameServiceDTO.Out updateUserName(UpdateUserNameServiceDTO.In in) {
        int dbResult = userInfoMapper.updateUserInfo(in.to());
        return UpdateUserNameServiceDTO.Out.from(dbResult);

    }
}
