package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.ytcomment.popular.Service.DTO.DetailPageCommentServiceDTO;
import site.ytcomment.popular.mapper.DTO.DetailPageCommentDbDTO;
import site.ytcomment.popular.mapper.DetailPageCommentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailPageCommentService {

//    private final PageCommentMapper pageCommentMapper;
    private final DetailPageCommentMapper detailPageCommentMapper;

    public List<DetailPageCommentServiceDTO.Out> detailPageComment(DetailPageCommentServiceDTO.In detailPageCommentServiceDTOIn) {
        List<DetailPageCommentDbDTO.Out> serviceResult = detailPageCommentMapper.selectDetailPageComment(detailPageCommentServiceDTOIn.to());

        return serviceResult.stream()
                .map(DetailPageCommentServiceDTO.Out::from)
                .collect(Collectors.toList());
    }
}
