package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.scrap.FindVideoLikeServiceDTO;
import site.ytcomment.popular.mapper.DTO.scrap.FindVideoLikeDbDTO;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindVideoLikeService {

    private final UserVideoLikeMapper userVideoLikeMapper;

    public FindVideoLikeServiceDTO.Out countVideoLike(FindVideoLikeServiceDTO.In in){
        FindVideoLikeDbDTO.Out dbResult = userVideoLikeMapper.countUserVideoLike(in.to());
        // db에 값이 없음
        if (dbResult.equals(0)){
            return FindVideoLikeServiceDTO.Out.from(dbResult, null, null);
        } else {
            // 이미 좋아요를 눌렀을 때
            return FindVideoLikeServiceDTO.Out.from(dbResult, in.getUserId(), in.getVideoId());
        }
    }

}
