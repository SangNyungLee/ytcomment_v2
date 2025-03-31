package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.ChangeEmailAuthServiceDTO;
import site.ytcomment.popular.mapper.ChangeEmailAuthMapper;

@Service
@RequiredArgsConstructor
public class CheckEmailAuthService {

    private final ChangeEmailAuthMapper changeEmailAuthMapper;

    public void updateUserAuth(ChangeEmailAuthServiceDTO.In in){
        System.out.println(in.getEmail());
        changeEmailAuthMapper.updateAuth(in.to());
    }
}
