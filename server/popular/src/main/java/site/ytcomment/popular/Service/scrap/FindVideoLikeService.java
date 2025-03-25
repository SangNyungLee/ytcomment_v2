package site.ytcomment.popular.Service.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.scrap.FindVideoLikeServiceDTO;
import site.ytcomment.popular.mapper.DTO.scrap.FindVideoLikeDbDTO;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

@Service
@RequiredArgsConstructor
public class FindVideoLikeService {

    private final UserVideoLikeMapper userVideoLikeMapper;

    public FindVideoLikeServiceDTO.Out countVideoLike(FindVideoLikeServiceDTO.In in){
        FindVideoLikeDbDTO.Out isUserLike = userVideoLikeMapper.countUserVideoLike(in.to());
        // 유저가 좋아요 눌렀으면 취소, 안 눌렀으면 추가
        return FindVideoLikeServiceDTO.Out.from(isUserLike, in.getUserId(), in.getVideoId());
    }

}
