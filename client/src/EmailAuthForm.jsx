import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function emailAuthPage () {
    const [userEmail, setUserEmail] = useState("");
    const [authCode, setAuthCode] = useState("");
    const navigate = useNavigate();
    const API_BASE_URL = import.meta.env.VITE_API_URL;
    const sendUserAuthCode = async () => {
      alert("인증코드가 전송되었습니다.");
      const result = await axios.post(`${API_BASE_URL}/api/email/send`, {email : userEmail});
    }

    const checkUserAuthCode = async () => {
      const result = await axios.post(`${API_BASE_URL}/api/email/check`, {email : userEmail, authNum : authCode});
      if (result.data.message === "0"){
        alert("인증에 성공했습니다.")
        // 인증 성공하고나서 cookie or session에 토큰 저장하는 로직 추가하면 될듯
        navigate("/");
      }
      else{
        alert("인증에 실패했습니다. 인증코드를 다시 확인해주세요.");
        setAuthCode("");
      }
    }
    return (
        <div className="mom">
      <div className="login">
        <div style={{ textAlign: "center", paddingTop: "30px", paddingBottom: "30px"}}> 
            <h1 style={{ color: "#4d627b", fontSize: "2em" }}>이메일 인증</h1>
        </div>
        <div className="loginForm" style={{ marginBottom: "3rem" }}>
          <form className="formTag">
            <div>
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
                onClick={sendUserAuthCode}
                style={{
                  color: "white",
                  backgroundColor: "#d53535",
                  marginLeft: "0.5rem",
                }}>
                인증코드 전송
              </button>
            </div >
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
            >인증하기</button>
          </form>
        </div>
      </div>
    </div>
  );
}