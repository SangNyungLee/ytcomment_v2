package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Service.YoutubeGetVideoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeGetVideoService youtubeGetvideoService;

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeGetvideoService.searchVideos();
    }

}
