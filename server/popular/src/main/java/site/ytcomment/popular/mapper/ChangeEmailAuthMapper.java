package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.ChangeEmailAuthDbDTO;

@Mapper
public interface ChangeEmailAuthMapper {
    void updateAuth(ChangeEmailAuthDbDTO.In in);
}
