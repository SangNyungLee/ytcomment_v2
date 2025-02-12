package site.ytcomment.popular.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * NoArgsConstructor를 사용하는 이유
 * 1. Spring은 Json을 Java로 변환할 때 기본 생성자를 사용함
 * 2. @Builder만 있으면 기본 생성자가 없어서 변환실패
 * 3. 따라서 @NoArgsConstructor로 기본 생성자를 만들어줘야됨
 * */
@Getter
@NoArgsConstructor // JSON 역직렬화를 위해 사용
@AllArgsConstructor // Builder패턴 사용하려고 씀
@Builder
public class YoutubeMainTrendingServiceRequestDTO {

    // 한 페이지당 카드 12개씩 보여줄거임
    private static final int ITEMS_PER_PAGE = 12;
    private int page;
    private int newCategory;

    // Pagination 값 계산하는 메서드
    public int calculateStartIndex() {
        return (this.page - 1) * ITEMS_PER_PAGE;
    }
}
