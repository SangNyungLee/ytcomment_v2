import { getCookie } from "./GetApi";
import axios from "axios";
const getLogin = async ({userId, userPw, setUserId, setUserPw}) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
    try {
      const result = await axios.post(`${API_BASE_URL}/api/auth/login`, {
        userId,
        userPw,
      });
      if(result.status === 200) {
        alert("로그인에 성공하셨습니다.!");
        sessionStorage.setItem("userName", result.data.userId);
        getCookie("token", result.data.token);
      }
    } catch (error) {
      if (error.status == 401) {
        alert("아이디와 비밀번호를 확인해주세요");
        setUserId("");
        setUserPw("");
      } else if (error.status === 403){
        alert("이메일 인증 후 사용가능합니다.");
        useNavigate("/emailAuthPage");
      }
    }
  };

export default getLogin;