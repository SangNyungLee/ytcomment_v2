import { useEffect, useState } from "react";

const CountDownTimer = ({ initialTime = 300, isActive }) => {
  const [time, setTime] = useState(initialTime);

  useEffect(() => {
    let timer;

    if (isActive && time > 0) {
      timer = setInterval(() => {
        setTime((prev) => prev - 1);
      }, 1000);
    }

    return () => clearInterval(timer);
  }, [isActive, time]);

  const formatTime = (seconds) => {
    // padStart : 문자열의 길이를 맞추기 위해 앞쪽에 특정 문자를 채워주는 함수
    const min = String(Math.floor(seconds / 60)).padStart(2, "0");
    const sec = String(seconds % 60).padStart(2, "0");
    return `${min} : ${sec}`;
  };

  if (!isActive && time <= 0) return null;
  return (
    <div style={{ color: "red", fontSize: "0.8rem" }}>
      남은 시간: {formatTime(time)}
    </div>
  );
};

export default CountDownTimer;
