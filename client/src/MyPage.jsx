import { useState } from "react";
import { LogOut, Edit, Save } from "lucide-react";
import "./css/MyPage.css";

const Mypage = () => {
  const [isEditing, setIsEditing] = useState(false);
  const [userInfo, setUserInfo] = useState({
    nickname: "테스트계정",
    email: "test@example.com",
    contact: "",
    notification: "모든 알림 받기"
  });

  const handleEdit = () => {
    if (isEditing) {
      console.log("닉네임 변경됨:", userInfo.nickname);
    }
    setIsEditing(!isEditing);
  };

  const handleChange = (e) => {
    setUserInfo({ ...userInfo, nickname: e.target.value });
  };

  const handleLogout = () => {
    console.log("로그아웃됨");
  };

  return (
    <div className="myPageContainer">
      <div className="p-6 max-w-4xl mx-auto border rounded-lg shadow-md bg-white">
        {/* 프로필 영역 */}
        <div className="flex items-center justify-between p-4 border-b">
          <div className="flex items-center gap-4">
            <div className="w-20 h-20 bg-gray-300 rounded-full flex items-center justify-center text-xl font-bold">
              {userInfo.nickname.charAt(0)}
            </div>
            <div>
              {!isEditing ? (
                <>
                  <h2 className="text-2xl font-semibold">{userInfo.nickname}님</h2>
                  <p className="text-gray-500">스크랩: 45 댓글: 128 좋아요: 372</p>
                </>
              ) : (
                <input
                  name="nickname"
                  value={userInfo.nickname}
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
                value={userInfo.email}
                className="border rounded-lg px-3 py-2 w-full bg-gray-100"
                disabled
              />
            </div>
            <div>
              <label className="text-sm text-gray-600">연락처</label>
              <input
                name="contact"
                value={userInfo.contact}
                className="border rounded-lg px-3 py-2 w-full bg-gray-100"
                disabled
              />
            </div>
            <div>
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
            </div>
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
          <div className="grid grid-cols-3 gap-4 mt-4">
            {[1, 2, 3].map((_, i) => (
              <div key={i} className="border rounded-lg p-4 shadow-sm bg-gray-100">
                <div className="w-full h-32 bg-gray-300 rounded-lg"></div>
                <h4 className="mt-2 text-sm font-semibold">스크랩된 영상 제목 {i + 1}</h4>
                <p className="text-xs text-gray-500">조회수 5.2만</p>
                <div className="flex justify-between mt-2">
                  <button className="px-3 py-1 border rounded-lg text-xs">더보기</button>
                  <button className="px-3 py-1 bg-black text-white rounded-lg text-xs">
                    스크랩
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* 로그아웃 버튼 */}
        <div className="mt-6 text-center">
          <button
            onClick={handleLogout}
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
