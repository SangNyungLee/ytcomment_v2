import axios from "axios";
import Cookies from "js-cookie";

const UserVideoLike = async (video) => {
    const token = Cookies.get("token");
    const API_BASE_URL = import.meta.env.VITE_API_URL;
    if (!token) {
        alert("로그인 한 사용자만 사용 가능한 기능입니다.");
        return;
    }
    try {
        
        const response = await axios.post(
            `${API_BASE_URL}/api/addUserLike`,
            {
                videoId: video
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        if (response.data.result === '0'){
            alert("영상이 성공적으로 스크랩 되었습니다.")
        } else{
            alert("영상 스크랩이 취소되었습니다.")
        }
        return true;
    } catch (error) {
        console.error("스크랩 실패:", error);
        alert("스크랩 중 오류가 발생했습니다.");
        return false;
    }
};

export default UserVideoLike;
