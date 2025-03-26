import axios from "axios";
import Cookies from "js-cookie";

const userScrapData = async ({ page, size }) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const token = Cookies.get("token");
  try {
    const response = await axios.post(
      `${API_BASE_URL}/api/getUserLikeList`,
      { page, size },
      { headers: { Authorization: `Bearer ${token}` } }
    );
    return response.data;
  } catch (error) {
    console.error("데이터 받아오기 실패");
  }
};

export default userScrapData;
