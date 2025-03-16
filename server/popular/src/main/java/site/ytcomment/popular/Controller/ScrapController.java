package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Controller.DTO.scrap.FindVideoLikeControllerDTO;
import site.ytcomment.popular.Controller.DTO.scrap.UserScrapPageControllerDTO;
import site.ytcomment.popular.Service.DTO.scrap.UserScrapPageServiceDTO;
import site.ytcomment.popular.Service.scrap.FindVideoLikeService;
import site.ytcomment.popular.Service.scrap.UserLikeListService;
import site.ytcomment.popular.Service.scrap.UserVideoLikeService;
import site.ytcomment.popular.config.jwt.CustomUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScrapController {

    private final UserVideoLikeService userVideoLikeService;
    private final FindVideoLikeService findVideoLikeService;
    private final UserLikeListService userLikeListService;
    /*
    해당 유저가 해당 영상을 좋아요 했는지 확인
    1. 좋아요 한 영상이 없다.
     -> 좋아요를 하고 성공 반환
    2. 좋아요 한 영상이 이미 있다.
     -> 해당 데이터 삭제하고 삭제했다고 반환
     */

    @PostMapping("/addUserLike")
    public ResponseEntity<?> userVideoLike(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @RequestBody FindVideoLikeControllerDTO.In in) {
        // 이미 좋아요를 했는지 확인
        FindVideoLikeControllerDTO.Out serviceResult = FindVideoLikeControllerDTO.Out
                .from(findVideoLikeService.countVideoLike(in.to(userDetails.getUserId())));
        return ResponseEntity.status(HttpStatus.OK)
                .body(userVideoLikeService.userScrapVideo(serviceResult.to()));
    }
    @PostMapping("/getUserLikeList")
    public List<UserScrapPageControllerDTO.Out> userVideoLikeList(@AuthenticationPrincipal CustomUserDetails userDetails){
        List<UserScrapPageServiceDTO.Out> serviceResult = userLikeListService.userLikeList(UserScrapPageControllerDTO.In.to(userDetails.getUserId()));
        return serviceResult.stream()
                .map(UserScrapPageControllerDTO.Out::from)
                .collect(Collectors.toList());
    }
}
