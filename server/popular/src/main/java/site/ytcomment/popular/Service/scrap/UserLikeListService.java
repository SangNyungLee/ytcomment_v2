package site.ytcomment.popular.Service.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.scrap.UserScrapPageServiceDTO;
import site.ytcomment.popular.mapper.DTO.scrap.UserScrapPageDbDTO;
import site.ytcomment.popular.mapper.UserVideoLikeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLikeListService {

    private final UserVideoLikeMapper userVideoLikeMapper;

    public List<UserScrapPageServiceDTO.Out> userLikeList(UserScrapPageServiceDTO.In in){
        List<UserScrapPageDbDTO.Out> result = userVideoLikeMapper.selectUserScrapPage(in.to());

        return result.stream()
                .map(UserScrapPageServiceDTO.Out::from)
                .collect(Collectors.toList());
    }
}
