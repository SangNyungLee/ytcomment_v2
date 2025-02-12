package site.ytcomment.popular.Controller.DTO;

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
public class ControllerTrendingRequestDTO {
    private int page; // 페이지 위치
    private int newCategory; // 특정 카테고리 눌렀을 때 걔들만 가져오게 해야됨
}
