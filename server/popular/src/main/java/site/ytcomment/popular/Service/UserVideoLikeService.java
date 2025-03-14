package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.UserVideoLikeServiceDTO;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

@Service
@RequiredArgsConstructor
public class UserVideoLikeService {

    private final UserVideoLikeMapper userVideoLikeMapper;
    // 반환값 고민좀 해봐야 될듯
    public void userScrapVideo(UserVideoLikeServiceDTO.In userVideoLikeServiceDTO) {
        userVideoLikeMapper.insertUserVideoLike(userVideoLikeServiceDTO.to());
    }

}
