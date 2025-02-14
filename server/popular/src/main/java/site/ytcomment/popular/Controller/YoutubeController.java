package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.*;
import site.ytcomment.popular.Service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeGetVideoService youtubeGetvideoService;
    private final TrendingService trendingService;
    private final GetTotalPageService getTotalPageService;
    private final DetailPageCommentService detailPageCommentService;
    private final DetailPageStatisticsService detailPageStatisticsService;
    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeGetvideoService.searchVideos();
    }

    @PostMapping("/trending")
    public List<CardResponseControllerDTO> getTrending(@RequestBody CardRequestControllerDTO CardRequestControllerDTO) {
        return trendingService.getTrendingService(CardRequestControllerDTO);
    }

    // 모든 영상 개수를 가져오는 api
    @GetMapping("/totalPage")
    public TotalPageResponseControllerDTO getTotalPage() {
        return getTotalPageService.getTotalPage();
    }

    // Card를 눌렀을 때 나오는 화면의 댓글을 가져오는 api
    @PostMapping("/getPageComment")
    public List<DetailPageResponseControllerDTO> getDetailPageComment(@RequestBody DetailPageRequestControllerDTO detailPageRequestControllerDTO) {
        return detailPageCommentService.detailPageComment(detailPageRequestControllerDTO);
    }

    @PostMapping("/getPageStatistics")
    public DetailPageStatisticsResControllerDTO getPageStatistics(@RequestBody DetailPageStatisticsReqControllerDTO reqDTO) {
        return detailPageStatisticsService.getPageStatistics(reqDTO);
    }
}
