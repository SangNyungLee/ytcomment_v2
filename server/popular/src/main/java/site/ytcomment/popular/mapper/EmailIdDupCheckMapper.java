package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.EmailIdDupCheckDbDTO;

@Mapper
public interface EmailIdDupCheckMapper {
    Integer findByEmailId(EmailIdDupCheckDbDTO.In in);
}
