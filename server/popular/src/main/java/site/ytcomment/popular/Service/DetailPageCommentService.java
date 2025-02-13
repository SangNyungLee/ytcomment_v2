package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Controller.DTO.DetailPageRequestControllerDTO;
import site.ytcomment.popular.Controller.DTO.DetailPageResponseControllerDTO;
import site.ytcomment.popular.Service.DTO.DetailPageRequestServiceDTO;
import site.ytcomment.popular.Service.DTO.DetailPageResponseServiceDTO;
import site.ytcomment.popular.mapper.PageCommentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailPageCommentService {

    private final PageCommentMapper pageCommentMapper;

    public List<DetailPageResponseControllerDTO> detailPageComment(DetailPageRequestControllerDTO detailPageRequestControllerDTO) {
        DetailPageRequestServiceDTO detailPageRequestServiceDTO = DetailPageRequestServiceDTO.builder()
                .id(detailPageRequestControllerDTO.getId())
                .build();
        List<DetailPageResponseServiceDTO> serviceResults = pageCommentMapper.selectComment(detailPageRequestServiceDTO);

        return serviceResults.stream()
                .map(this::convertToControllerDTO)
                .collect(Collectors.toList());
    }

    private DetailPageResponseControllerDTO convertToControllerDTO(DetailPageResponseServiceDTO detailPageResponseServiceDTO) {

        // 이거 LocalDateTime형식 그대로 보내면 toList되는 과정에서 PublishedAt이 배열로 와버림;
        String formattedDate = detailPageResponseServiceDTO.getPublishedAt().toString();
        return DetailPageResponseControllerDTO.builder()
                .id(detailPageResponseServiceDTO.getId()) // 이거 나중에 지우고 videoId = id로 변경하기
                .videoId(detailPageResponseServiceDTO.getVideoId())
                .likeCount(detailPageResponseServiceDTO.getLikeCount())
                .textOriginal(detailPageResponseServiceDTO.getTextOriginal())
                .authorDisplayName(detailPageResponseServiceDTO.getAuthorDisplayName())
                .authorProfileImageUrl(detailPageResponseServiceDTO.getAuthorProfileImageUrl())
                .publishedAt(formattedDate)
                .build();
    }
}
