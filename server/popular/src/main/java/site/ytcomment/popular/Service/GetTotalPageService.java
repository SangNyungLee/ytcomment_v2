package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Controller.DTO.TotalPageResponseControllerDTO;
import site.ytcomment.popular.mapper.TotalPageMapper;

@Service
@RequiredArgsConstructor
public class GetTotalPageService{

    private final TotalPageMapper totalPageMapper;

    public TotalPageResponseControllerDTO getTotalPage(){
        System.out.println("실행부분!!");
        System.out.println("totalPage에서 가져온 값 : " + totalPageMapper.totalPage());
        return TotalPageResponseControllerDTO.builder()
                .totalPage(totalPageMapper.totalPage().getTotalPage())
                .build();
    }
}
