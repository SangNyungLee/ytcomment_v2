package site.ytcomment.popular.Service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailDupServiceDTO;
import site.ytcomment.popular.common.Enum.ResponseCode;
import site.ytcomment.popular.mapper.EmailDupCheckMapper;

@Service
@RequiredArgsConstructor
public class EmailDupCheckService {

    private final EmailDupCheckMapper emailDupCheckMapper;

    // 이메일 중복체크를 해야 하는데 카카오는 이메일을 제공하지 않아서 어쩔 수 없이 id값으로 중복확인을 하는데
    // 이러한 이유 때문에 재사용을 못해서 서비스를 또 새로 만들었음
    public String checkEmailDup(EmailDupServiceDTO.In in) {
        Integer result = emailDupCheckMapper.findByEmail(in.to());
        System.out.println("이메일 중복확인값 " + result);
        // count로 이메일 개수 세서 중복확인을 했는데 이게 좋은 방법인지는 잘 모르겠음
        if (result.equals(1)) {
            return ResponseCode.실패.getCode();
        }
        else{
            return ResponseCode.성공.getCode();
        }
    }
}
