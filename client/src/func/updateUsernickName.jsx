import axios from "axios"
import Cookies from "js-cookie"
import { getCookie } from "./GetApi";
const updateUserNickname = async (userName) => {
	try {
		const token = Cookies.get("token");
		const result = await axios.post(`${import.meta.env.VITE_API_URL}/api/updateUserName`,
			{userName},
			{
				headers : {Authorization : `Bearer ` + token}
			}
		);
		// 성공 시
		if (result.data.result == 1){
			// 성공하면 쿠키 다시 발급해서 쿠키 안에 userName같이 보내서 업데이트 시켜버리기
			getCookie("token", token, userName, 0);
			return (1);
		} else{
			alert("오류가 발생했습니다.");
		}
	} catch (error) {
		console.error("오류입니다.", error);
	}
}

export default updateUserNickname;