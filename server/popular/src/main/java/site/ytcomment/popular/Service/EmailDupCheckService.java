package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailDupServiceDTO;
import site.ytcomment.popular.mapper.EmailDupCheckMapper;

@Service
@RequiredArgsConstructor
public class EmailDupCheckService {

    private final EmailDupCheckMapper emailDupCheckMapper;

    public String checkEmailDup(EmailDupServiceDTO.In in) {
        Integer result = emailDupCheckMapper.findByEmail(in.to());
        System.out.println("결과값 : " + result);
        if (result.equals(1)) {
            System.out.println("이미 아이디 있음");
            return "fail";
        }
        else{
            System.out.println("가입 가능한 아이디");
            return "success";
        }
    }
}
