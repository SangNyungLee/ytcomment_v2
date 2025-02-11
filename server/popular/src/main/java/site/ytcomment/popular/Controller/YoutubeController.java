package site.ytcomment.popular.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ytcomment.popular.Controller.DTO.TestResponseDTO;
import site.ytcomment.popular.DTO.Test1;
import site.ytcomment.popular.DTO.Test2;
import site.ytcomment.popular.DTO.Test3;
import site.ytcomment.popular.Service.YoutubeTrendingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeTrendingService youtubeTrendingService;

    @GetMapping("/getVideos")
    public String getVideos() {
        return youtubeTrendingService.searchVideos();
    }

    @PostMapping("/test")
    public TestResponseDTO test() {
        Test1 getTest1 = Test1.builder()
                    .id(1)
                    .name("test1")
                    .email("test1@test.com")
                    .build();

        Test2 getTest2 = Test2.builder()
                    .id(2)
                    .name("test2")
                    .email("test2@test.com")
                    .build();

        Test3 getTest3 = Test3.builder()
                    .id(3)
                    .name("test3")
                    .email("test3@test.com")
                    .build();


        return TestResponseDTO.builder()
                .test1(getTest1)
                .test2(getTest2)
                .test3(getTest3)
                .build();
    }
}
