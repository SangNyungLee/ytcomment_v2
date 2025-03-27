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
  const [userIdError, setUserIdError] = useState("");
  const [userNameError, setUserNameError] = useState("");

  const API_BASE_URL = import.meta.env.VITE_API_URL;

  // 유효성 검사 함수
  const isValidUserId = (value) => /^[a-zA-Z0-9]+$/.test(value);
  const isValidUserName = (value) => /^[가-힣a-zA-Z0-9]+$/.test(value);

  // 입력 핸들러
  const handleEmailChange = (e) => {
    const email = e.target.value;
    setUserEmail(email);
    if (!validateEmail(email)) {
      setEmailError("유효한 이메일 주소를 입력하세요.");
    } else {
      setEmailError("");
    }
  };

  const handlePasswordChange = (e) => {
    const password = e.target.value;
    setUserPw(password);
    if (!validatePassword(password)) {
      setPasswordError(
        "비밀번호는 영문, 숫자, 특수문자를 포함해 8자 이상이어야 합니다."
      );
    } else {
      setPasswordError("");
    }
  };

  const handlePasswordCheck = (e) => {
    const value = e.target.value;
    setUserPwCheck(value);
    setIsPasswordMatch(value === userPw);
  };

  const handleUserIdChange = (e) => {
    const id = e.target.value;
    setUserId(id);
    if (!isValidUserId(id)) {
      setUserIdError("아이디는 영문과 숫자만 사용할 수 있습니다.");
    } else {
      setUserIdError("");
    }
  };

  const handleUserNameChange = (e) => {
    const name = e.target.value;
    setUserName(name);
    if (!isValidUserName(name)) {
      setUserNameError("닉네임은 한글, 영문, 숫자만 사용할 수 있습니다.");
    } else {
      setUserNameError("");
    }
  };

  // 중복 체크
  const checkUserEmail = async () => {
    if (!validateEmail(userEmail)) {
      alert("올바른 이메일 형식을 입력하세요.");
      return;
    }

    try {
      const response = await axios.get(
        `${API_BASE_URL}/api/email/check-email`,
        {
          params: { email: userEmail },
        }
      );

      if (response.data === 0) {
        alert("가입 가능한 이메일입니다.");
        setUserEmailCheck(true);
      } else {
        alert("중복된 이메일입니다.");
        setUserEmailCheck(false);
      }
    } catch (error) {
      console.error("이메일 중복 확인 에러", error);
    }
  };

  const checkUserId = async () => {
    if (!isValidUserId(userId)) {
      alert("아이디는 영문과 숫자만 입력 가능합니다.");
      return;
    }

    try {
      const result = await axios.get(`${API_BASE_URL}/api/email/check-id`, {
        params: { id: userId },
      });

      if (result.data === 0) {
        alert("가입 가능한 아이디입니다.");
        setUserIdCheck(true);
      } else {
        alert("중복된 아이디입니다.");
        setUserIdCheck(false);
      }
    } catch (error) {
      console.error("아이디 중복 확인 에러", error);
    }
  };

  // 회원가입 요청
  const handleSignup = async () => {
    if (!validatePassword(userPw)) {
      alert("비밀번호는 영문, 숫자, 특수문자를 포함해 8자 이상이어야 합니다.");
      return;
    }

    if (
      isPasswordMatch &&
      userEmailCheck &&
      userIdCheck &&
      !userIdError &&
      !userNameError
    ) {
      try {
        const response = await axios.post(
          `${API_BASE_URL}/api/email/email-signup`,
          {
            userId,
            userEmail,
            userPw,
            userName,
            social: "Email",
          }
        );

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
            {/* 이메일 */}
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
                style={{
                  color: "white",
                  backgroundColor: "#d53535",
                  marginLeft: "0.5rem",
                }}
                onClick={checkUserEmail}
              >
                이메일 중복확인
              </button>
              {emailError && <div className="error">{emailError}</div>}
              {userEmailCheck && (
                <div className="success">가입 가능한 이메일입니다.</div>
              )}
            </div>

            {/* 아이디 */}
            <div className={`idCheck ${userIdCheck ? "hasSuccess" : ""}`}>
              <input
                type="text"
                className="userId"
                value={userId}
                onChange={handleUserIdChange}
                placeholder="아이디"
              />
              <button
                type="button"
                className="btn"
                style={{
                  color: "white",
                  backgroundColor: "#d53535",
                  marginLeft: "0.5rem",
                }}
                onClick={checkUserId}
              >
                아이디 중복확인
              </button>
              {userIdError && <div className="error">{userIdError}</div>}
              {userIdCheck && !userIdError && (
                <div className="success">가입 가능한 아이디입니다.</div>
              )}
            </div>

            {/* 비밀번호 */}
            <div className={`pwCheck ${passwordError ? "hasError" : ""}`}>
              <input
                type="password"
                className="userPw"
                value={userPw}
                onChange={handlePasswordChange}
                placeholder="비밀번호"
              />
              {passwordError && <span className="error">{passwordError}</span>}
              <input
                type="password"
                className="userPwCheck"
                value={userPwCheck}
                onChange={handlePasswordCheck}
                placeholder="비밀번호 확인"
              />
              {isPasswordMatch && (
                <span className="success">비밀번호가 일치합니다.</span>
              )}
            </div>

            {/* 닉네임 */}
            <div className="nicknameCheck">
              <input
                type="text"
                className="userName"
                value={userName}
                onChange={handleUserNameChange}
                placeholder="닉네임"
              />
              {userNameError && <div className="error">{userNameError}</div>}
            </div>

            {/* 회원가입 버튼 */}
            <button
              type="button"
              style={{ color: "white", backgroundColor: "#6bcf70" }}
              className="btn signupBtn"
              disabled={
                !isPasswordMatch ||
                !userEmailCheck ||
                !userIdCheck ||
                !userName ||
                userIdError ||
                userNameError
              }
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
