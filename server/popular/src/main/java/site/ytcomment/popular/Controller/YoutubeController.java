package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.*;
import site.ytcomment.popular.Service.*;
import site.ytcomment.popular.Service.DTO.CardServiceDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeGetVideoService youtubeGetvideoService;
    private final GetTotalPageService getTotalPageService;
    private final DetailPageCommentService detailPageCommentService;
    private final DetailPageStatisticsService detailPageStatisticsService;
    private final CardTrendingService cardTrendingService;

    @PostMapping("/trending")
    public List<CardControllerDTO.Out> getTrendings(@RequestBody CardControllerDTO.In cardControllerDTOIn){
        // TODO 2025.02.17 sanglee 서비스 메서드명 수정하기
        List<CardServiceDTO.Out> controllerResult = cardTrendingService.getTrendingService(cardControllerDTOIn.to());

        return controllerResult.stream()
                .map(CardControllerDTO.Out::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeGetvideoService.searchVideos();
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
