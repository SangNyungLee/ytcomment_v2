import React, { useState } from "react";
import "./css/LsideStyle.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AccordionFlush from "./Accordion";
import { BsFillBarChartLineFill } from "react-icons/bs";
import { Link, useNavigate } from "react-router-dom";
import getLogin from "./func/Login";
import Cookies from "js-cookie";
import logout from "./func/logout";

export default function Lside() {
  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");
  const cookieString = document.cookie;
  const navigate = useNavigate();
  const userName = Cookies.get("username");

  const handleLogin = () => {
    // 로그인 실패하면 input값들 초기화 하기 위해서 만듬
    getLogin({ userId, userPw, setUserId, setUserPw, navigate });
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      getLogin({ userId, userPw, setUserId, setUserPw, navigate });
    }
  };

  return (
    <div className="mainSide">
      <div className="title">
        <span className="commentLogo">
          <BsFillBarChartLineFill className="myLogo" />
          <span className="additionalText">YTComment </span>
        </span>
      </div>
      {cookieString ? (
        <div>
          <div className="welcome">
            <span style={{ color: "black", fontWeight: "700" }}>
              {userName}
            </span>{" "}
            님 환영합니다.~
          </div>
          <button className="btnLogin" onClick={logout}>
            로그아웃
          </button>
        </div>
      ) : (
        <div>
          <input
            type="text"
            value={userId}
            className="idInput"
            placeholder="아이디"
            onChange={(e) => setUserId(e.target.value)}
          ></input>
          <input
            type="password"
            value={userPw}
            className="idInput"
            placeholder="비밀번호"
            onChange={(e) => setUserPw(e.target.value)}
            onKeyDown={handleKeyPress}
          ></input>
          <button className="btnLogin" onClick={handleLogin}>
            로그인
          </button>
          <br />
          <div className="autoSign" style={{ textAlign: "center" }}>
            {/* <input type="checkbox" className="loginCheckbox" />
            <span className="autoLogin">자동로그인</span> */}
            <span className="signup">
              <span>
                <Link
                  to={"/signup"}
                  style={{ color: "white", textDecoration: "none" }}
                >
                  회원가입 / SNS가입
                </Link>
              </span>
            </span>
          </div>
        </div>
      )}
      <AccordionFlush />
    </div>
  );
}
