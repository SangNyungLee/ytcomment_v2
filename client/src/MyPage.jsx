import { useEffect, useState } from "react";
import { LogOut, Edit, Save } from "lucide-react";
import "./css/MyPage.css";
import userScrapData from "./scrap/UserScrapData";
import formatNumber from "./func/FormatNumber";
import { Link } from "react-router-dom";
import UserVideoLike from "./scrap/UserLike";
import Cookies from "js-cookie";
import logout from "./func/logout";
import getUserInfo from "./func/getUserInfo";
import updateUserNickname from "./func/updateUsernickName";
const Mypage = () => {
	const username = Cookies.get("username");
	const [loading, setLoading] = useState(true);
	const [items, setItems] = useState([]);
	const [isEditing, setIsEditing] = useState(false);
	const [userInfo, setUserInfo] = useState({
		count : 0,
		userName: "",
		userEmail: "",
		id: ""
	  });

	const handleEdit = async () => {
		if (isEditing && (userInfo.userName != username)) {
				const isUpdate = await updateUserNickname(userInfo.userName);
				if(isUpdate){
					alert("닉네임 수정이 완료되었습니다.");
				}
		}
		setIsEditing(!isEditing);
	};
	
	const handleChange = (e) => {
		setUserInfo({ ...userInfo, userName: e.target.value });
	};

	const fetchData = async () => {
		setLoading(true);
		try {
			const data = await userScrapData();
			const userInfo = await getUserInfo();
			setUserInfo(userInfo);
			setItems(data);
		} catch (error) {
			console.error("데이터 로드 실패", error)
		} finally {
			setLoading(false);
		}
	};

	// 스크랩기능
	const handleVideoLike = async (videoId) => {
		const result = await UserVideoLike(videoId);
		if (result) {
			fetchData();
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	return (
		<div className="myPageContainer">
			<div className="p-6 max-w-4xl mx-auto border rounded-lg shadow-md bg-white">
			{/* 프로필 영역 */}
			<div className="flex items-center justify-between p-4 border-b">
				<div className="flex items-center gap-4">
				<div>
					<img src="https://picsum.photos/250/250" className="w-20 h-20 bg-gray-300 rounded-full flex items-center justify-center text-xl font-bold"/>
				</div>
				<div>
					{!isEditing ? (
					<>
						<h2 className="text-2xl font-semibold">{username}님</h2>
						{/* <p className="text-gray-500">스크랩: {userInfo.count} 댓글: 128 좋아요: 372</p> */}
						<p className="text-gray-500">스크랩 개수 : {userInfo.count}개</p>
					</>
					) : (
					<input
						name="nickname"
						value={userInfo.userName || ""}
						onChange={handleChange}
						className="border rounded-lg px-3 py-2 text-lg font-semibold"
					/>
					)}
				</div>
				</div>
				<button
				onClick={handleEdit}
				className="px-4 py-2 bg-red-500 text-white rounded-lg flex items-center gap-2 hover:bg-red-600"
				>
				{isEditing ? <Save size={16} /> : <Edit size={16} />}
				{isEditing ? "저장" : "프로필 수정"}
				</button>
			</div>

			{/* 기본 정보 */}
			<div className="p-4">
				<h3 className="text-lg font-semibold mb-2">기본 정보</h3>
				<div className="grid grid-cols-2 gap-4">
				<div>
					<label className="text-sm text-gray-600">이메일</label>
					<input
					name="email"
					value={userInfo.userEmail}
					className="border rounded-lg px-3 py-2 w-full bg-gray-100"
					disabled
					/>
				</div>
				<div>
					<label className="text-sm text-gray-600">아이디</label>
					<input
					name="contact"
					value={userInfo.id}
					className="border rounded-lg px-3 py-2 w-full bg-gray-100"
					disabled
					/>
				</div>
				{/* <div>
					<label className="text-sm text-gray-600">알림 설정</label>
					<select
					name="notification"
					value={userInfo.notification}
					className="border rounded-lg px-3 py-2 w-full bg-gray-100"
					disabled
					>
					<option>모든 알림 받기</option>
					<option>중요 알림만 받기</option>
					<option>알림 받지 않기</option>
					</select>
				</div> */}
				</div>
			</div>

			{/* 내 활동 */}
			<div className="border-t p-4 mt-4">
				<h3 className="text-lg font-semibold mb-2">내 활동</h3>
				<div className="flex gap-6 border-b">
				<button className="text-red-500 font-semibold border-b-2 border-red-500 pb-2">
					내 스크랩
				</button>
				<button className="text-gray-500 pb-2">내 댓글</button>
				<button className="text-gray-500 pb-2">좋아요 누른 영상</button>
				</div>
				
				{/* 스크랩 리스트 */}
				<div className="mt-4">
				{items.map((item, i) => (
					<div key={i} className="scrap-item">
					<div className="mypage-scrap-thumbnail">
						<img src={item.thumbnails} alt={item.id} className="scrap-thumbnail" />
					</div>
					<div className="scrap-info">
						<h4 className="text-sm font-semibold">{item.title}</h4>
						<p className="text-xs text-gray-500">조회수 : {formatNumber(item.channelViewCount)}</p>
						<div className="scrap-buttons">
							<div>
								<Link
								to="/page"
								state={{ videoId: item.id }}
								className="btn moreBtn"
								>
								더보기
								</Link>
							</div>
							<div className="flex-1 text-right flex justify-end items-center">
								<button className="scrap-delete-btn text-sm text-red-500 hover:underline"
									onClick={() => handleVideoLike(item.id)}>
								삭제
								</button>
							</div>
						</div>
					</div>
					</div>
				))}
				</div>
			</div>

			{/* 로그아웃 버튼 */}
			<div className="mt-6 text-center">
				<button
				onClick={logout}
				className="px-4 py-2 bg-red-500 text-white rounded-lg flex items-center gap-2 hover:bg-red-600"
				>
				<LogOut size={16} /> 로그아웃
				</button>
			</div>
			</div>
		</div>
	);
};

export default Mypage;
