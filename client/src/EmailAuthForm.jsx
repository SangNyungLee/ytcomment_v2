import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import CountDownTimer from "./func/CountDownTimer";

export default function emailAuthPage() {
  const [userEmail, setUserEmail] = useState("");
  const [authCode, setAuthCode] = useState("");
  const [showTimer, setShowTimer] = useState(false); // 타이머 시작 컨트롤

  const location = useLocation(); // state로 받은 값을 받기 위해 사용함
  const navigate = useNavigate();
  const API_BASE_URL = import.meta.env.VITE_API_URL;

  // state로 받은 값을 바로 email에 적용될 수 있게 설정
  useEffect(() => {
    if (location.state?.userEmail) {
      setUserEmail(location.state.userEmail);
    }
  }, [location]);

  const sendUserAuthCode = async () => {
    alert("인증코드가 전송되었습니다.");
    const result = await axios.post(`${API_BASE_URL}/api/email/send`, {
      email: userEmail,
    });
    setShowTimer(false); // 이전 타이머 초기화
    setTimeout(() => setShowTimer(true), 0); // 리셋하고 다시 시작
  };

  const checkUserAuthCode = async () => {
    const result = await axios.post(`${API_BASE_URL}/api/email/check`, {
      email: userEmail,
      authNum: authCode,
    });
    if (result.data.message === "0") {
      alert("인증에 성공했습니다.");
      navigate("/");
    } else {
      alert("인증에 실패했습니다. 인증코드를 다시 확인해주세요.");
      setAuthCode("");
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
          <h1 style={{ color: "#4d627b", fontSize: "2em" }}>이메일 인증</h1>
        </div>
        <div className="loginForm" style={{ marginBottom: "3rem" }}>
          <form className="formTag">
            <div>
              <input
                type="text"
                className="userEmail"
                value={userEmail}
                readOnly
                placeholder="이메일주소"
                style={{
                  color: "gray",
                  backgroundColor: "#f5f5f5",
                }}
              />
              <button
                type="button"
                className="btn"
                onClick={sendUserAuthCode}
                style={{
                  color: "white",
                  backgroundColor: "#d53535",
                  marginLeft: "0.5rem",
                }}
              >
                인증코드 전송
              </button>
              {showTimer && (
                <CountDownTimer initialSeconds={300} isActive={showTimer} />
              )}
            </div>
            <input
              type="text"
              className="userName"
              value={authCode}
              onChange={(e) => setAuthCode(e.target.value)}
              placeholder="인증코드"
            />
            <button
              type="button"
              style={{ color: "white", backgroundColor: "#6bcf70" }}
              onClick={checkUserAuthCode}
              className="btn signupBtn"
            >
              인증하기
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
