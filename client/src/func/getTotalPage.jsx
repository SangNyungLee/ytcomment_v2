import axios from "axios";

const getTotalPage = async ({ categoryId }) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const result = await axios.post(`${API_BASE_URL}/api/totalPage`, {
    categoryId,
  });
  console.log("totalPage Result : ", result);
  return result.data.totalPage;
};

export default getTotalPage;
