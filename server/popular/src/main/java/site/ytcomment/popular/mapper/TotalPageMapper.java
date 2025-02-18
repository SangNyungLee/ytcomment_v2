package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.TotalPageDbDTO;

@Mapper
public interface TotalPageMapper {
    TotalPageDbDTO.Out totalPage(TotalPageDbDTO.In totalPageDbDTOIn);
}