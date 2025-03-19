import React, { useState } from "react";
import axios from "axios";
import "./css/SignupForm.css";
import validateEmail from "./func/login/validateEmail";
import validatePassword from "./func/login/validatePassword";

function SignupForm() {
  const [userEmail, setUserEmail] = useState("");
  const [userIdCheck, setUserIdCheck] = useState(false);
  const [userEmailCheck, setUserEmailCheck] = useState(false);
  const [userId, setUserId] = useState("");
  const [userPw, setUserPw] = useState("");
  const [userPwCheck, setUserPwCheck] = useState("");
  const [userName, setUserName] = useState("");
  const [isPasswordMatch, setIsPasswordMatch] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const API_BASE_URL = import.meta.env.VITE_API_URL;

  // // 이메일 형식 검증
  // const validateEmail = (email) => {
  //   const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  //   return emailRegex.test(email);
  // };

  // // 비밀번호 검증 (영문, 숫자, 특수문자 포함 8자 이상)
  // const validatePassword = (password) => {
  //   const passwordRegex =
  //     /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
  //   return passwordRegex.test(password);
  // };

  // 이메일 입력 핸들러
  const handleEmailChange = (e) => {
    const email = e.target.value;
    setUserEmail(email);
    if (!validateEmail(email)) {
      setEmailError("유효한 이메일 주소를 입력하세요.");
    } else {
      setEmailError("");
    }
  };

  // 비밀번호 입력 핸들러
  const handlePasswordChange = (e) => {
    const password = e.target.value;
    setUserPw(password);
    if (!validatePassword(password)) {
      setPasswordError("비밀번호는 영문, 숫자, 특수문자를 포함해 8자 이상이어야 합니다.");
    } else {
      setPasswordError("");
    }
  };

  // 비밀번호 확인 핸들러
  const handlePasswordCheck = (e) => {
    const { value } = e.target;
    setUserPwCheck(value);
    setIsPasswordMatch(value === userPw);
  };

  // 이메일 중복 검사
  const checkUserEmail = async () => {
    if (!validateEmail(userEmail)) {
      alert("올바른 이메일 형식을 입력하세요.");
      return;
    }

    try {
      const response = await axios.get(`${API_BASE_URL}/api/email/check-email`, {
        params: { email: userEmail },
      });

      if (response.data === 0) {
        alert("가입 가능한 이메일입니다.");
        setUserEmailCheck(true);
      } else {
        alert("중복된 이메일입니다.");
        setUserEmailCheck(false);
        setUserEmail(""); // 중복된 경우 입력 필드 초기화
      }
    } catch (error) {
      console.error("에러", error);
    }
  };

  // 아이디 중복 검사
  const checkUserId = async () => {
    try {
      const result = await axios.get(`${API_BASE_URL}/api/email/check-id`, {
        params: { id: userId },
      });

      if (result.data === 0) {
        alert("가입 가능한 아이디입니다.");
        setUserIdCheck(true);
      } else {
        alert("중복된 아이디입니다.");
      }
    } catch (error) {
      console.error("에러", error);
    }
  };

  // 회원가입 처리
  const handleSignup = async () => {
    if (!validatePassword(userPw)) {
      alert("비밀번호는 영문, 숫자, 특수문자를 포함해 8자 이상이어야 합니다.");
      return;
    }

    if (isPasswordMatch) {
      try {
        const response = await axios.post(`${API_BASE_URL}/api/email/email-signup`, {
          userId,
          userEmail,
          userPw,
          userName,
          social: "Email",
        });

        if (response.data === 0) {
          alert("회원가입이 완료되었습니다!");
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
        <div style={{ textAlign: "center", padding: "30px 0" }}>
          <h1 style={{ color: "#4d627b", fontSize: "2em" }}>회원가입</h1>
        </div>
        <div className="loginForm" style={{ marginBottom: "3rem" }}>
          <form className="formTag">
			{/* 이메일 입력 필드 */}
			<div className={`emailCheck ${emailError ? "hasError" : ""}`}>
			<input
				type="text"
				className="userEmail"
				value={userEmail}
				onChange={handleEmailChange}
				placeholder="이메일주소"
			/>
			<button
				type="button"
				className="btn"
				style={{ color: "white", backgroundColor: "#d53535", marginLeft: "0.5rem" }}
				onClick={checkUserEmail}
			>
				이메일 중복확인
			</button>

			{/* 오류 메시지가 있으면 표시 */}
			{emailError && <div className="error">{emailError}</div>}
			{userEmailCheck && <div className="success">가입 가능한 이메일입니다.</div>}
			</div>

			{/* 아이디 입력 필드 */}
			<div className={`idCheck ${userIdCheck ? "hasSuccess" : ""}`}>
			<input
				type="text"
				className="userId"
				value={userId}
				onChange={(e) => setUserId(e.target.value)}
				placeholder="아이디"
			/>
			<button
				type="button"
				className="btn"
				style={{ color: "white", backgroundColor: "#d53535", marginLeft: "0.5rem" }}
				onClick={checkUserId}
			>
				아이디 중복확인
			</button>

			{userIdCheck && <div className="success">가입 가능한 아이디입니다.</div>}
			</div>

			{/* 비밀번호 입력 필드 */}
			<div className={`pwCheck ${passwordError ? "hasError" : ""}`}>
			<input
				type="password"
				className="userPw"
				value={userPw}
				onChange={handlePasswordChange}
				placeholder="비밀번호"
			/>
			{/* 오류 메시지가 있으면 표시 */}
			{passwordError && <span className="error">{passwordError}</span>}

			<input
				type="password"
				className="userPwCheck"
				value={userPwCheck}
				onChange={handlePasswordCheck}
				placeholder="비밀번호 확인"
			/>
			{/* 비밀번호 일치 메시지 */}
			{isPasswordMatch && <span className="success">비밀번호가 일치합니다.</span>}
			</div>

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
