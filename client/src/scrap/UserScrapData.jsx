import axios from "axios";
import Cookies from "js-cookie";

const userScrapData = async () => {
    const token = Cookies.get("token");
    console.log("버튼 누름")
    try {
        const response = await axios.post(
            "http://localhost:8080/api/getUserLikeList", 
            {}, {headers : {Authorization : `Bearer ${token}`,}},
        )
        console.log(response);
        return response.data;
    } catch (error) {
        console.error("데이터 받아오기 실패");
    }
}

export default userScrapData;