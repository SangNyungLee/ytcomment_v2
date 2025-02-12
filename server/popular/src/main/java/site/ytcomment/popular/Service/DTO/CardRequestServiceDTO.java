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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestServiceDTO {

    private int page; // JSON 역직렬화를 위해 사용
    private int newCategory; // Builder패턴 사용하려고 씀
    private int startIndex; // (page - 1) * itemsPerPage
}
