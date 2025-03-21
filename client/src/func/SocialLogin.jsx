import React from "react";
import axios from "axios";
import "../css/KakaoButton.css";
import kakaoLogo from "../assets/kakao_logo.png"; // 로고 파일 경로

const SocialKaKao = () => {
  const kakaoClientId = import.meta.env.VITE_KAKAO_JAVASCRIPT_KEY;
  const API_BASE_URL = import.meta.env.VITE_API_URL;

  const initiateKakaoLogin = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/api/auth/kakaoReq`);
      window.location.href = response.data; // TODO: 팝업 처리 시 window.open 사용
    } catch (error) {
      alert("카카오 로그인 초기화 실패");
      console.error(error);
    }
  };

  return (
    <button className="kakaoLoginBtn" onClick={initiateKakaoLogin}>
      <img src={kakaoLogo} alt="kakao" className="kakaoIcon" />
      카카오 로그인
    </button>
  );
};

export default SocialKaKao;