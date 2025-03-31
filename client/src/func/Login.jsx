import { getCookie } from "./GetApi";
import axios from "axios";
const getLogin = async ({ userId, userPw, setUserId, setUserPw, navigate }) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  try {
    const result = await axios.post(`${API_BASE_URL}/api/auth/login`, {
      userId,
      userPw,
    });
    if (result.status === 200) {
      alert("로그인에 성공하셨습니다.!");

      // 헤더에서 JWT 토큰 가져오기
      const token = result.headers.authorization?.split(" ")[1]; // "Bearer token값" → "token값"
      const username = result.data;
      getCookie("token", token, username, 1);
    }
  } catch (error) {
    if (error.status == 401) {
      alert("아이디와 비밀번호를 확인해주세요");
      setUserId("");
      setUserPw("");
    } else if (error.status === 403) {
      alert("이메일 인증 후 사용가능합니다.");
      navigate("/emailAuthPage", { state: { userEmail: error.response.data } });
    }
  }
};

export default getLogin;
