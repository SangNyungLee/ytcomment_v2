package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsServiceDTO;
import site.ytcomment.popular.mapper.DTO.DetailPageStatisticsDbDTO;
import site.ytcomment.popular.mapper.DetailPageStatisticsMapper;

@Service
@RequiredArgsConstructor
public class DetailPageStatisticsService {

    private final DetailPageStatisticsMapper detailPageStatisticsMapper;

    public DetailPageStatisticsServiceDTO.Out getPageStatistics(DetailPageStatisticsServiceDTO.In in) {
        DetailPageStatisticsDbDTO.Out serviceResult = detailPageStatisticsMapper.selectDetailPageStatistics(in.to());
        return DetailPageStatisticsServiceDTO.Out.from(serviceResult);
    }
}
