package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsReqServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsResServiceDTO;

@Mapper
public interface PageStatisticsMapper {
    DetailPageStatisticsResServiceDTO selectDetailPageStatistics(DetailPageStatisticsReqServiceDTO detailPageStatisticsReqServiceDTO);
}
