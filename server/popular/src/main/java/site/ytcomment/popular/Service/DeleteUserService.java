package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.DeleteUserInfoServiceDTO;
import site.ytcomment.popular.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final UserMapper userMapper;

    public void deleteUserByEmail(DeleteUserInfoServiceDTO.In in) {
        userMapper.deleteUserByEmail(in.to());
    }
}
