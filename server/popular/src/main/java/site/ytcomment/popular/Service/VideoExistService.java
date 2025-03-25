package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.scrap.FindVideoLikeServiceDTO;
import site.ytcomment.popular.mapper.DTO.scrap.FindVideoLikeDbDTO;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

@Service
@RequiredArgsConstructor
public class VideoExistService {

    private final UserVideoLikeMapper userVideoLikeMapper;

    public FindVideoLikeServiceDTO.Out findVideo(FindVideoLikeServiceDTO.In in){
        FindVideoLikeDbDTO.Out isUserLike = userVideoLikeMapper.findVideoLikeById(in.to());
        // video값을 DB에 있는지 찾아보기 반환 값은 int값임
        return FindVideoLikeServiceDTO.Out.fromFindVideo(isUserLike);
    }
}
