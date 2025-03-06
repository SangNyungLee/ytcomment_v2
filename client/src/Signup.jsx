import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./css/Signup.css";
import { getCookie } from "./func/GetApi";
import SocialKaKao from "./func/SocialLogin";

function Signup() {
  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");
  const getLogin = async () => {
    const result = await axios.post("http://localhost:8080/api/auth/login", {
      userId,
      userPw,
    });
    if (result.data === 1) {
      alert("아이디와 비밀번호를 확인해주세요");
      setUserId("");
      setUserPw("");
    } else if (result.data === 2){
      alert("이메일 인증 후 사용가능합니다.");
      useNavigate("/emailAuthPage");
    }
    else {
      getCookie("token", result.data.token);
      sessionStorage.setItem("userName", result.data.user.username);
      alert("로그인에 성공하셨습니다.!");
    }
    
  };
  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      getLogin();
    }
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
              type="text"
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
              onClick={getLogin}
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
          {/* <GoogleLoginButton /> */}
          {/* <GithubLoginButton /> */}
        </div>
      </div>
    </div>
  );
}

export default Signup;
