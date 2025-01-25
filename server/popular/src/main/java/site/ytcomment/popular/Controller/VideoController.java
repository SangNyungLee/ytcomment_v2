package site.ytcomment.popular.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.Service.VideoService;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor // 필드삽입 하면 안 되니깐 이걸로 생성자 자동생성
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/getVideos") // Youtube API사용해서 Video데이터들 받아오는 api
    public String getVideos() {
        return null;
    }

    @GetMapping("/trending") // 메인페이지에 있는 영상 목록 가져오는거
    public ObjectMapper trending(@RequestParam int page, @RequestParam int newCategory) {
        return videoService.getTrendingVideos(page, newCategory);
    }
}
