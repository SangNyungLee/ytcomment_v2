import axios from "axios";
import logout from "./func/logout";
import { deleteCookie } from "./func/GetApi";

const axiosInstance = axios.create({
  baseURL: import.meta.VITE_API_URL, // 내 API 주소
  withCredentials: true, // 쿠키 인증 사용시 true(쿠키 자동으로 전송해준다고 생각하면 됨)
});

// 응답 인터셉터
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    console.log(error);
    const status = error.response?.status;
    if (status === 401) {
      alert("아이디와 비밀번호를 확인해주세요.");
    } else if (status === 403) {
      const userEmail = error.response?.data;
      alert("이메일 인증 후 사용 가능합니다.");
      // 나중에 처리할 수 있도록 error 객체에 이메일 첨부
      error.userEmail = userEmail;
    } else if (status === 444) {
      // 로그인이 풀리고, 쿠키 전부 삭제
      logout();
      deleteCookie();
    }

    return Promise.reject(error);
  }
);

export default axiosInstance;
