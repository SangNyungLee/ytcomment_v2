package site.ytcomment.popular.Repository;

import site.ytcomment.popular.Repository.DTO.영상목록조회결과DbDto;
import site.ytcomment.popular.Repository.DTO.영상목록조회요청DbDto;

public class 영상목록조회Dao {
    // private final CommonDao 공통dao;
    public 영상목록조회결과DbDto 좋아요순영상목록조회(final 영상목록조회요청DbDto 입력){
        // return 공통dao.select("com.apr.영상.xml", "좋아요순영상목록조회", 입력);
        // return 공통dao.insert("com.apr.영상.xml", "영상삽입", 입력);
        return null;
    }

    public void 영상삽입(final 영상목록조회요청DbDto 입력){
        // return 공통dao.insert("com.apr.영상.xml", "영상삽입", 입력);
    }

}
