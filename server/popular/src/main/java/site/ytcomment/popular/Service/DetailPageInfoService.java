package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.DTO.DetailPageInfoDTO;
import site.ytcomment.popular.mapper.DetailPageInfoMapper;

@Service
@RequiredArgsConstructor
public class DetailPageInfoService {
    private final DetailPageInfoMapper detailPageInfoMapper;

    public DetailPageInfoDTO.Out getDetailPageInfoByVideoId(DetailPageInfoDTO.In in) {
        return detailPageInfoMapper.selectDetailPageInfo(in);

    }
}
