package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.TotalPageServiceDTO;
import site.ytcomment.popular.mapper.DTO.TotalPageDbDTO;
import site.ytcomment.popular.mapper.TotalPageMapper;

@Service
@RequiredArgsConstructor
public class TotalPageService {

    private final TotalPageMapper totalPageMapper;

    public TotalPageServiceDTO.Out getTotalPage(TotalPageServiceDTO.In in) {
        TotalPageDbDTO.Out serviceResult = totalPageMapper.totalPage(in.to());
        return TotalPageServiceDTO.Out
                .builder()
                .totalPage(serviceResult.getTotalPage())
                .build();
    }
}
