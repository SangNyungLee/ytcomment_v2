import axios from "axios";
import Cookies from "js-cookie";

// ✅ 수정된 userScrapData
const userScrapData = async ({ page, size }) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const token = Cookies.get("token");
  page = (page - 1) * size;
  try {
    const response = await axios.post(
      `${API_BASE_URL}/api/getUserLikeList`,
      { page, size },
      { headers: { Authorization: `Bearer ${token}` } }
    );
    return response.data;
  } catch (error) {
    console.error("데이터 받아오기 실패", error);
    return []; // fallback 리턴도 추가해주자
  }
};

export default userScrapData;
