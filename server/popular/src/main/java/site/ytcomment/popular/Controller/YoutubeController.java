package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Controller.DTO.*;
import site.ytcomment.popular.Service.*;
import site.ytcomment.popular.Service.DTO.CardServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageCommentServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageStatisticsServiceDTO;
import site.ytcomment.popular.Service.DTO.TotalPageServiceDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeGetVideoService youtubeGetvideoService;
    private final DetailPageCommentService detailPageCommentService;
    private final DetailPageStatisticsService detailPageStatisticsService;
    private final CardTrendingService cardTrendingService;
    private final TotalPageService totalPageService;

    @PostMapping("/trending")
    public List<CardControllerDTO.Out> getTrendings(@RequestBody CardControllerDTO.In cardControllerDTOIn){
        List<CardServiceDTO.Out> controllerResult = cardTrendingService.getTrendingService(cardControllerDTOIn.to());

        return controllerResult.stream()
                .map(CardControllerDTO.Out::from)
                .collect(Collectors.toList());
    }

    // Card를 눌렀을 때 나오는 화면의 댓글을 가져오는 api
    @PostMapping("/getPageComment")
    public List<DetailPageCommentControllerDTO.Out> getDetailPageComment(@RequestBody DetailPageCommentControllerDTO.In detailPageCommentDTOIn) {
        List<DetailPageCommentServiceDTO.Out> controllerResult = detailPageCommentService.detailPageComment(detailPageCommentDTOIn.to());
        return controllerResult.stream()
                .map(DetailPageCommentControllerDTO.Out::from)
                .collect(Collectors.toList());
    }

    // 카테고리 별로 모든 영상 개수를 가져오는 api, 이 값을 가져와서 페이지네이션에 사용함
    // categoryId가 0인 경우에는 Main화면에서 불러오는 경우 나머지는 카테고리(1, 10, 15, 20)를 눌러서 값을 가져와야됨
    @PostMapping("/totalPage")
    public TotalPageControllerDTO.Out getTotalPage(@RequestBody TotalPageControllerDTO.In totalPageDTOIn) {

        TotalPageServiceDTO.Out totalPage = totalPageService.getTotalPage(totalPageDTOIn.to());
        return TotalPageControllerDTO.Out.from(totalPage);
    }

    // 영상을 눌렀을 때 나오는 상세페이지의 통계값을 가져오는 api
    @PostMapping("/getPageStatistics")
    public DetailPageStatisticsControllerDTO.Out getPageStatistics(@RequestBody DetailPageStatisticsControllerDTO.In in) {
        DetailPageStatisticsServiceDTO.Out pageStatistics = detailPageStatisticsService.getPageStatistics(in.to());
        return DetailPageStatisticsControllerDTO.Out.from(pageStatistics);
    }

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeGetvideoService.searchVideos();
    }



}
