package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.LoginAuthDbDTO;

@Mapper
public interface LoginAuthMapper {
     LoginAuthDbDTO.Out selectUserPw(LoginAuthDbDTO.In in);
}
