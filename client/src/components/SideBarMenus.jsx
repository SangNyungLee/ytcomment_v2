import React from "react";
import { useNavigate } from "react-router-dom";
import { BsHouse, BsPerson, BsStar, BsChatRightText } from "react-icons/bs";

const SidebarMenus = () => {
  const navigate = useNavigate();

  return (
    <div className="sidebar-links">
      <ul className="main-menu">
        <li onClick={() => navigate("/")}>
          <BsHouse /> 홈
        </li>
        <li onClick={() => navigate("/mypage")}>
          <BsPerson /> 마이페이지
        </li>
        <li onClick={() => navigate("/scrapVideo")}>
          <BsStar /> 나의 영상 스크랩
        </li>
        <li>
          <BsChatRightText /> 커뮤니티
        </li>
      </ul>

      <div className="board-section">
        <p className="board-title">게시판</p>
        <ul className="board-menu">
          <li onClick={() => navigate("/notice")}>공지사항</li>
          <li onClick={() => navigate("/freeboard")}>자유게시판</li>
          {/* <li onClick={() => navigate("/qna")}>질문게시판</li>
          <li onClick={() => navigate("/debate")}>토론게시판</li> */}
        </ul>
      </div>
    </div>
  );
};

export default SidebarMenus;
