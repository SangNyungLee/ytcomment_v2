package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Controller.DTO.DetailPageStatisticsReqControllerDTO;
import site.ytcomment.popular.Controller.DTO.DetailPageStatisticsResControllerDTO;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsReqServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsResServiceDTO;
import site.ytcomment.popular.mapper.PageStatisticsMapper;

@Service
@RequiredArgsConstructor
public class DetailPageStatisticsService {

    private final PageStatisticsMapper pageStatisticsMapper;

    public DetailPageStatisticsResControllerDTO getPageStatistics(DetailPageStatisticsReqControllerDTO reqDTO) {
        DetailPageStatisticsReqServiceDTO reqServiceDTO = DetailPageStatisticsReqServiceDTO.builder()
                .id(reqDTO.getId())
                .build();
        DetailPageStatisticsResServiceDTO resServiceDTO = pageStatisticsMapper.selectDetailPageStatistics(reqServiceDTO);

        return DetailPageStatisticsResControllerDTO.builder()
                .id(resServiceDTO.getId())
                .channelViewCount(resServiceDTO.getChannelViewCount())
                .channelFavoriteCount(resServiceDTO.getChannelFavoriteCount())
                .channelCommentCount(resServiceDTO.getChannelCommentCount())
                .channelLikeCount(resServiceDTO.getChannelLikeCount())
                .publishedAt(resServiceDTO.getPublishedAt().toString())
                .build();
    }
}
