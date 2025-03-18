import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { getCookie } from "./GetApi";

const KakaoRedirectHandler = () => {
    const API_BASE_URL = import.meta.env.VITE_API_URL;
    const navigate = useNavigate();
    const [isProcessed, setIsProcessed] = useState(false);
    
    // 두 번씩 실행되는거 때문에 일단 해줬음, Restrict Mode를 해제하면 안 되긴하는데 개발환경에서만 두 번씩 실행되는거
    // 나중에 개발환경에서는 삭제하는게 좋을듯

    useEffect(() => {
        const handleKakaoLogin = async () => {
            try {
                const urlParams = new URLSearchParams(window.location.search);
                const code = urlParams.get("code");
                if (!code)
                    throw new Error("인가 코드가 없습니다.");
                
                if(!isProcessed){
                    const response = await axios.post(`${API_BASE_URL}/api/auth/kakao`, {code});
                    // URL에서 ?code=XXX 제거
                    window.history.replaceState({}, null, "/");

                    // 성공하면 메인페이지로 보냄
                    if (response.status == 200){
                        sessionStorage.setItem("userName", response.data.userId);
                        getCookie("token", response.data.token);
                    } else{
                        alert("로그인에 실패하였습니다.");
                        navigate("/");
                    }
                }
                setIsProcessed(true);
            } catch (error) {
                console.error("로그인 실패", error);
                // alert("로그인 중 오류 발생");
            }
        }
        handleKakaoLogin(); // 비동기로 실행
    }, [])

    return <p>로그인 중 ...</p>;
}

export default KakaoRedirectHandler;