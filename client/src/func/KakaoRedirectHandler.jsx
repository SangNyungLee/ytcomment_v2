import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"

const KakaoRedirectHandler = () => {
    const navigate = useNavigate();
    const [isProcessed, setIsProcessed] = useState(false);
    // 두 번씩 실행되는거 때문에 일단 해줬음, Restrict Mode를 해제하면 안 되긴하는데 개발환경에서만 두 번씩 실행되는거
    // 나중에 개발환경에서는 삭제하는게 좋을듯

    useEffect(() => {
        const handleKakaoLogin = async () => {
            try {
                const urlParams = new URLSearchParams(window.location.search);
                const code = urlParams.get("code");
                console.log("code : ", code);
                if (!code)
                    throw new Error("인가 코드가 없습니다.");
                
                if(!isProcessed){
                    const response = await axios.post("http://localhost:8080/auth/kakao", {code});
                    console.log("여기까지 들어옴?")
                    if (response == "success")
                        console.log("로그인 성공");
                    // 받은 토큰 저장하는 로직 작성
                    
                    // URL에서 ?code=XXX 제거
                    window.history.replaceState({}, null, "/");
    
                    //로그인 후 메인 페이지로 이동하게 하기
                    // navigate("/")
                }
                setIsProcessed(true);
            } catch (error) {
                console.error("로그인 실패", error);
                // alert("로그인 중 오류 발생");
            }
        }
        handleKakaoLogin(); // 비동기로 실행
    }, [navigate])

    return <p>로그인 중 ...</p>;
}

export default KakaoRedirectHandler;