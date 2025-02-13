package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.CardRequestControllerDTO;
import site.ytcomment.popular.Controller.DTO.CardResponseControllerDTO;
import site.ytcomment.popular.Controller.DTO.TotalPageResponseControllerDTO;
import site.ytcomment.popular.Service.GetTotalPageService;
import site.ytcomment.popular.Service.TrendingService;
import site.ytcomment.popular.Service.YoutubeGetVideoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeGetVideoService youtubeGetvideoService;
    private final TrendingService trendingService;
    private final GetTotalPageService getTotalPageService;
    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeGetvideoService.searchVideos();
    }

    @PostMapping("/trending")
    public List<CardResponseControllerDTO> getTrending(@RequestBody CardRequestControllerDTO CardRequestControllerDTO) {
        return trendingService.getTrendingService(CardRequestControllerDTO);
    }
    @GetMapping("/totalPage")
    // 모든 영상 개수를 가져오는 api
    public TotalPageResponseControllerDTO getTotalPage() {
        return getTotalPageService.getTotalPage();
    }
}
