package site.ytcomment.popular.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import site.ytcomment.popular.DTO.VideoResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    List<VideoResponseDTO> videos = new ArrayList<VideoResponseDTO>();
    //ObjectMapper mapper = new ObjectMapper();
    public ObjectMapper getTrendingVideos(int page, int newCategory){
        int itemsPerPage = 12; // 한 페이지당 12개씩 받아올 거임
        int startIndex = (page - 1) * itemsPerPage;



    }
}
