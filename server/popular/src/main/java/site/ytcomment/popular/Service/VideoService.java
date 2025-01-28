package site.ytcomment.popular.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.DTO.VideoResponseDTO;
import site.ytcomment.popular.Controller.DTO.특정정렬ReqDTO;
import site.ytcomment.popular.Controller.DTO.특정정렬ResDTO;
import site.ytcomment.popular.Enum.특정정렬조회구분코드Enum;
import site.ytcomment.popular.Repository.DTO.영상목록조회결과DbDto;
import site.ytcomment.popular.Repository.영상목록조회Dao;
import site.ytcomment.popular.Service.DTO.특정정렬InDTO;
import site.ytcomment.popular.Service.DTO.특정정렬OutDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final 영상목록조회Dao 영상목록조회dao;


    List<VideoResponseDTO> videos = new ArrayList<VideoResponseDTO>();
    //ObjectMapper mapper = new ObjectMapper();
    public ObjectMapper getTrendingVideos(int page, int newCategory){
        int itemsPerPage = 12; // 한 페이지당 12개씩 받아올 거임
        int startIndex = (page - 1) * itemsPerPage;

return null;

    }

    public List<특정정렬OutDTO> 특정정렬조회(특정정렬InDTO 입력){

        final var 특정정렬조회구분코드 = 특정정렬조회구분코드Enum.getEnum(입력.get구분코드());
//                return switch (특정정렬조회구분코드) {
//                    case 좋아요 -> 좋아요별조회(입력);
//                    case 최신 -> 최신별조회(입력);
//                    case 일자 -> 일자별조회(입력);
//                };

        if(특정정렬조회구분코드Enum.is일자별(입력.get구분코드())) {
            return 일자별조회(입력);
        }
    }


    private List<특정정렬ResDTO> 좋아요별조회(final 특정정렬InDTO 입력){
        영상목록조회결과DbDto 결과 = 영상목록조회dao.좋아요순영상목록조회(입력.to영상목록조회요청DbDto());
        return null;
    }

    private List<특정정렬ResDTO> 최신별조회(final 특정정렬ReqDTO 입력){
        return null;
    }

    private List<특정정렬ResDTO> 일자별조회(final 특정정렬ReqDTO 입력){
        return null;
    }


}
