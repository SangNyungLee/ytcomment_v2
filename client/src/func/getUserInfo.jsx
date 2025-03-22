import axios from "axios";
import Cookies from "js-cookie";
const getUserInfo = async () => {
	const token = Cookies.get("token");
	try {
		const result = await axios.get(`${import.meta.env.VITE_API_URL}/api/getUserInfo`,
			{
				headers: {
					Authorization : `Bearer ${token}`
				}
			}
		);
		return result.data;

	} catch (error) {
		console.error("오류 입니다.", error);
	}
}

export default getUserInfo;