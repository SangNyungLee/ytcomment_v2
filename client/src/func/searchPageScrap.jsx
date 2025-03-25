import axios from "axios";

const searchPageScrap = async (videoId) => {
  const API_BASE_URL = import.meta.env.VITE_API_URL;

  const response = await axios.post(`${API_BASE_URL}/api/findUserVideo`, {
    videoId: videoId,
  });
};

export default searchPageScrap;
