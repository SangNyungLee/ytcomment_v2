package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Controller.DTO.SingleVideoSaveControllerDTO;
import site.ytcomment.popular.Controller.DTO.scrap.FindVideoLikeControllerDTO;
import site.ytcomment.popular.Controller.DTO.scrap.UserScrapPageControllerDTO;
import site.ytcomment.popular.Service.DTO.scrap.UserScrapPageServiceDTO;
import site.ytcomment.popular.Service.SingleVideoSaveService;
import site.ytcomment.popular.Service.VideoExistService;
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
    private final VideoExistService videoExistService;
    private final SingleVideoSaveService singleVideoSaveService;
    /*
    해당 유저가 해당 영상을 좋아요 했는지 확인
    1. 좋아요 한 영상이 없다.
     -> 좋아요를 하고 성공 반환
    2. 좋아요 한 영상이 이미 있다.
     -> 해당 데이터 삭제하고 삭제했다고 반환
     */

    /*
     결과값 : 0 (DB에 저장된 영상이 없음)
     결과값 : 1 (DB에 저장된 영상이 있음)
     1. 조건문 사용
     2.1 있으면 DB저장하기
     2.2 없으면 DB저장안함
     3. DB에 있는 값 조회해서 return 해서 프론트에서 그대로 렌더링 해버리기
    */
    @PostMapping("/addUserLike")
    public ResponseEntity<?> userVideoLike(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @RequestBody FindVideoLikeControllerDTO.In in) {
        // videoId가 DB에 있는지 먼저 확인하기, 확인하고 없으면 DB에 데이터 저장할거임
        FindVideoLikeControllerDTO.Out videoExist
                = FindVideoLikeControllerDTO.Out.from(videoExistService.findVideo(in.toFindVideo()));
        // DB에 저장되어 있는 video면 지나가고 저장이 안 되어 있으면 api요청 보내서 저장하기
        singleVideoSaveService.saveVideoById(SingleVideoSaveControllerDTO.In.to(videoExist.getCount(), in.getVideoId()));

        // 좋아요를 했는지 확인
        FindVideoLikeControllerDTO.Out serviceResult = FindVideoLikeControllerDTO.Out
                .from(findVideoLikeService.countVideoLike(in.to(userDetails.getUserId())));
        return ResponseEntity.status(HttpStatus.OK)
                .body(userVideoLikeService.userScrapVideo(serviceResult.to()));
    }
    @PostMapping("/getUserLikeList")
    public List<UserScrapPageControllerDTO.Out> userVideoLikeList(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                                  @RequestBody UserScrapPageControllerDTO.In in) {
        List<UserScrapPageServiceDTO.Out> serviceResult = userLikeListService.userLikeList(in.to(userDetails.getUserId()));
        return serviceResult.stream()
                .map(UserScrapPageControllerDTO.Out::from)
                .collect(Collectors.toList());
    }
}
