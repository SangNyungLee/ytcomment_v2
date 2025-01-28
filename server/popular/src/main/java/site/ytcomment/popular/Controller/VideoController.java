package site.ytcomment.popular.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ytcomment.popular.DTO.VideoResponseDTO;
import site.ytcomment.popular.Controller.DTO.특정정렬ReqDTO;
import site.ytcomment.popular.Controller.DTO.특정정렬ResDTO;
import site.ytcomment.popular.Service.DTO.특정정렬OutDTO;
import site.ytcomment.popular.Service.VideoService;

import java.util.List;
import java.util.stream.Collectors;

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
    public ObjectMapper trending(@RequestParam VideoResponseDTO 비디오응답, @RequestParam int newCategory) {
        비디오응답.getPage();
        return null;
    }

    @GetMapping("/adf") // 메인페이지에 있는 영상 목록 가져오는거
    public List<특정정렬ResDTO> 특정정렬조회(@RequestParam @Validated 특정정렬ReqDTO 입력) {
        List<특정정렬OutDTO> 서비스결과목록 = videoService.특정정렬조회(입력.to특정정렬InDTO());
        // A -> B A.toB
        // B -> A from
        return 서비스결과목록.stream()
                .map(특정정렬ResDTO::from)
                .collect(Collectors.toList());
    }
}
