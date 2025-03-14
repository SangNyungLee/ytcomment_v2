package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Controller.DTO.scrap.UserVideoLikeControllerDTO;
import site.ytcomment.popular.Service.DTO.scrap.UserVideoLikeServiceDTO;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

@Service
@RequiredArgsConstructor
public class UserVideoLikeService {

    private final UserVideoLikeMapper userVideoLikeMapper;
    // 반환값 고민좀 해봐야 될듯

    public UserVideoLikeServiceDTO.Out userScrapVideo(UserVideoLikeServiceDTO.In userVideoLikeServiceDTO) {
        if(userVideoLikeServiceDTO.getCount() == 0){
            // 저장되어 있는 값이 없으면 저장하기
            userVideoLikeMapper.insertUserVideoLike(userVideoLikeServiceDTO.to());
            // 저장했을 때
            return UserVideoLikeServiceDTO.Out.from(ResponseCode.성공.getCode());
        } else {
            // 이미 저장되어 있는 값이 있으면 삭제하기
            userVideoLikeMapper.deleteUserVideoLike(userVideoLikeServiceDTO.to());
            // 삭제했을 때
            return UserVideoLikeServiceDTO.Out.from(ResponseCode.실패.getCode());
        }
    }

}
