import React from "react";
import KakaoLogin from "react-kakao-login";
import axios from "axios";
import { getCookie } from "./GetApi";
import "../css/KakaoButton.css";
const SocialKaKao = () => {
  // 기존 React 환경변수는 process.env로 시작했는데 vite에서는 import.meta.로 시작하면 됨
  const kakaoClientId = import.meta.env.VITE_KAKAO_JAVASCRIPT_KEY;
  const kakaoOnSuccess = async (data) => {
    console.log("데이터 받아온 값 : ", data);
    const idToken = data.response.access_token; // 엑세스 토큰 백엔드로 전달
    const result = await axios.post("http://localhost:8080/auth/kakao", {
      Token: idToken,
    });
    getCookie("token", result.data.token);
    sessionStorage.setItem("userName", result.data.user.username.nickname);
    alert("로그인에 성공하셨습니다.!");
  };
  const kakaoOnFailure = (error) => {
    alert("로그인에 실패하였습니다.", error);
  };

  return (
    <>
      <KakaoLogin
        className="kakao-login-button"
        style={{ lineHeight: "40px" }}
        token={kakaoClientId}
        onSuccess={kakaoOnSuccess}
        onFail={kakaoOnFailure}
      />
    </>
  );
};

export default SocialKaKao;
