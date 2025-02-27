package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.EmailDupDbDTO;

@Mapper
public interface EmailDupCheckMapper {
    Integer findByEmail(EmailDupDbDTO.In in);
}
