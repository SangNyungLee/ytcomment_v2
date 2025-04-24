import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Home,
  User,
  Star,
  MessagesSquare,
  ChevronDown,
  ChevronUp,
} from "lucide-react";

export default function SidebarMenus() {
  const navigate = useNavigate();

  // boardOpen = 자유게시판 보드 여는거
  // const [boardOpen, setBoardOpen] = useState(false);
  // const toggleBoardMenu = () => setBoardOpen(!boardOpen);
  // 게시판 사용할 때 밑에 있는거 붙여넣기
  // {
  //   boardOpen && (
  //     <ul className="board-menu">
  //       <li onClick={() => navigate("/notice")}>공지사항</li>
  //       <li onClick={() => navigate("/boardLists")}>자유게시판</li>
  //       {/* <li onClick={() => navigate("/qna")}>질문게시판</li>
  //     <li onClick={() => navigate("/debate")}>토론게시판</li> */}
  //     </ul>
  //   );
  // }
  return (
    <div className="sidebar-links">
      <ul className="main-menu">
        <li onClick={() => navigate("/")}>
          <Home size={18} style={{ marginRight: "8px" }} />홈
        </li>
        <li onClick={() => navigate("/mypage")}>
          <User size={18} style={{ marginRight: "8px" }} />
          마이페이지
        </li>
        <li onClick={() => navigate("/scrapVideo")}>
          <Star size={18} style={{ marginRight: "8px" }} />
          나의 영상 스크랩
        </li>
        {/* <li onClick={toggleBoardMenu}>
          <MessagesSquare size={18} style={{ marginRight: "8px" }} />
          게시판
          {boardOpen ? (
            <ChevronUp size={16} style={{ marginLeft: "auto" }} />
          ) : (
            <ChevronDown size={16} style={{ marginLeft: "auto" }} />
          )}
        </li> */}
      </ul>
    </div>
  );
}
