package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.Service.DTO.TotalPageResponseServiceDTO;

@Mapper
public interface TotalPageMapper {
    TotalPageResponseServiceDTO totalPage();
}