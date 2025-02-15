package site.ytcomment.popular.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse {
    private int statusCode;
    private String message;

    // 성공했을 때
    public static BaseResponse success(String data) {
        return new BaseResponse(200, data);
    }

    // 실패했을 때
    public static BaseResponse fail(int errorCode, String errorMessage) {
        return new BaseResponse(errorCode, errorMessage);
    }
}
