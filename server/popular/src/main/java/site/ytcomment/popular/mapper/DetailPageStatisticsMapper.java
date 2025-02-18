package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.DetailPageStatisticsDbDTO;

@Mapper
public interface DetailPageStatisticsMapper {
    DetailPageStatisticsDbDTO.Out selectDetailPageStatistics(DetailPageStatisticsDbDTO.In detailPageStatisticsDbDTOIn);
}
