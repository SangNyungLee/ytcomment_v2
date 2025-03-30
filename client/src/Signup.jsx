import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./css/Signup.css";
import SocialKaKao from "./func/SocialLogin";
import getLogin from "./func/Login";
function Signup() {
  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");
  const navigate = useNavigate();
  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      getLogin({ userId, userPw, setUserId, setUserPw, navigate });
    }
  };

  const handleLogin = () => {
    // 로그인 실패하면 input값들 초기화 하기 위해서 만듬
    getLogin({ userId, userPw, setUserId, setUserPw, navigate });
  };
  return (
    <div className="mom">
      <div className="login">
        <div
          style={{
            textAlign: "center",
            paddingTop: "30px",
            paddingBottom: "30px",
          }}
        >
          <h1 style={{ color: "#4d627b", fontSize: "2em" }}>로그인</h1>
        </div>
        <div className="loginForm">
          <div className="formTag">
            <input
              type="text"
              value={userId}
              className={userId}
              placeholder="아이디"
              onChange={(e) => {
                setUserId(e.target.value);
              }}
            />
            <input
              type="password"
              value={userPw}
              className={userPw}
              placeholder="비밀번호"
              onChange={(e) => {
                setUserPw(e.target.value);
              }}
              onKeyDown={handleKeyPress}
            />
            <button
              type="button"
              style={{ color: "white", backgroundColor: "#D32F2F" }}
              className="btn loginBtn"
              onClick={handleLogin}
            >
              로그인
            </button>
            <Link
              to={"/signupForm"}
              style={{ color: "white", backgroundColor: "#6bcf70" }}
              className="btn signupBtn"
            >
              <button
                style={{
                  border: "0px",
                  color: "white",
                  backgroundColor: "#6bcf70",
                }}
              >
                회원가입
              </button>
            </Link>
          </div>
        </div>
        <div className="dddd">
          {/* <input type="checkBox" style={{ marginRight: "10px" }} />
          <span>자동로그인</span> */}
        </div>
        <div className="socialLogin">
          <div className="loginSNS">SNS 계정으로 로그인</div>
          <SocialKaKao />
        </div>
      </div>
    </div>
  );
}

export default Signup;
