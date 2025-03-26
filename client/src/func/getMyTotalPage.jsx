import axios from "axios";
import Cookies from "js-cookie";
const getMyTotalPage = async () => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const token = Cookies.get("token");
  const result = await axios.get(`${API_BASE_URL}/api/getUserLikeInfo`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return result.data.count;
};

export default getMyTotalPage;
