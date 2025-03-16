import React, { useState } from "react";
import axios from "axios";
import "./css/SignupForm.css";
function SignupForm() {
  const [userEmail, setUserEmail] = useState("");
  const [userIdCheck, setUserIdCheck] = useState(false);
  const [userEmailCheck, setUserEmailCheck] = useState(false);
  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");
  const [userPwCheck, setUserPwCheck] = useState("");
  const [userName, setUserName] = useState("");
  const [isPasswordMatch, setIsPasswordMatch] = useState(false);
  const PasswordCheck = (e) => {
    const { value } = e.target;
    setUserPwCheck(value);
    setIsPasswordMatch(value === userPw);
  };

  const checkUserEmail = async () => {
    try {
      const response = await axios
        .get("http://localhost:8080/api/email/check-email",
          {params : {email : userEmail}}
        )
        .then((res) => {
          if (res.data === 0) {
            // 가입가능한 이메일이면
            alert("가입가능한 이메일입니다.");
            setUserEmailCheck(true);
          } else {
            alert("중복된 이메일 입니다.");
            setUserEmailCheck(false);
            // 중복된 이메일이면 input창 비워주기
            setUserEmail(""); 
          }
        });
    } catch (error) {
      console.error("에러", error);
    }
  };

  const checkUserId = async () => {
    try {
      const result = await axios.get("http://localhost:8080/api/email/check-id", {params : {id : userId}})
      if (result.data === 0){
        alert("가입 가능한 아이디 입니다.");
        setUserIdCheck(true);
      }
      else
        alert("중복된 아이디 입니다.");
    } catch (error) {
      console.error("에러", error);
    }
  }
  const handleSignup = async () => {
    if (isPasswordMatch) {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/email/email-signup",
          {
            userId,
            userEmail,
            userPw,
            userName,
            social : "Email",
          }
        );
        if (response.data === 0) {
          alert("회원가입이 완료되었습니다.!!");
          window.location.href = "/";
        } else {
          alert("회원가입 오류!!");
        }
      } catch (error) {
        console.error("signup error", error);
      }
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
          <h1 style={{ color: "#4d627b", fontSize: "2em" }}>회원가입</h1>
        </div>
        <div className="loginForm" style={{ marginBottom: "3rem" }}>
          <form className="formTag">
            <div style={{ marginBottom: userEmailCheck ? "0px" : "10px" }}>
              <input
                type="text"
                className="userEmail"
                value={userEmail}
                onChange={(e) => setUserEmail(e.target.value)}
                placeholder="이메일주소"
              />
              <button
                type="button"
                className="btn"
                style={{
                  color: "white",
                  backgroundColor: "#d53535",
                  marginLeft: "0.5rem",
                }}
                onClick={checkUserEmail}
              >
                이메일 중복확인
              </button>
            </div >
            {userEmailCheck ? (
              <span className="userEmailTag">가입 가능한 이메일 입니다.</span>
            ) : null}
            <div style={{ marginBottom: userIdCheck ? "0px" : "10px" }}>
            <input type="text" className="userId" value={userId} onChange={(e) => setUserId(e.target.value)} 
              placeholder="아이디"
            />
                <button type="button" className="btn" 
                style={{ color: "white", backgroundColor: "#d53535", marginLeft: "0.5rem",}}
                onClick={checkUserId}>
                아이디 중복확인
              </button>
            </div>
            {userIdCheck ? (
              <span className="userEmailTag">가입 가능한 아이디 입니다.</span>
            ) : null}
            <input
              type="password"
              className="userPw"
              value={userPw}
              onChange={(e) => setUserPw(e.target.value)}
              placeholder="비밀번호"
            />
            <input
              type="password"
              className="userPwCheck"
              value={userPwCheck}
              onChange={PasswordCheck}
              placeholder="비밀번호확인"
              style={{ marginBottom: isPasswordMatch ? "0px" : "10px" }}
            />
            {isPasswordMatch ? (
              <span className="pwMatch">비밀번호가 일치합니다.</span>
            ) : null}
            <input
              type="text"
              className="userName"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              placeholder="닉네임"
            />
            <button
              type="button"
              style={{ color: "white", backgroundColor: "#6bcf70" }}
              className="btn signupBtn"
              // 비밀번호와 이메일이 중복이 아니고 닉네임까지 입력했을 때 활성화
              disabled={!isPasswordMatch || !userEmailCheck || !userName}
              onClick={handleSignup}
            >
              회원가입
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SignupForm;
