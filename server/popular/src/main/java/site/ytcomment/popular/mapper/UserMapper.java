package site.ytcomment.popular.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.ytcomment.popular.mapper.DTO.DeleteUserInfoDbDTO;

@Mapper
public interface UserMapper {
    void deleteUserByEmail(DeleteUserInfoDbDTO.In in);
}
