package site.ytcomment.popular.Service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.EmailIdDupServiceDTO;
import site.ytcomment.popular.mapper.EmailIdDupCheckMapper;

@Service
@RequiredArgsConstructor
public class EmailIdDupCheckService {

    private final EmailIdDupCheckMapper emailIdDupCheckMapper;

    public Integer EmailIdDupCheck(EmailIdDupServiceDTO.In in){
        return emailIdDupCheckMapper.findByEmailId(in.to());
    }
}
