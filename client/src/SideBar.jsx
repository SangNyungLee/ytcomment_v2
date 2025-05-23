import React, { useEffect, useRef, useState } from "react";
import styles from "./css/sidebar.module.css";
import Lside from "./LSide";
import { BsArrowBarRight } from "react-icons/bs";
import { ArrowBigRightDash } from "lucide-react";
const Sidebar = ({ width = 220 }) => {
  const [isOpen, setOpen] = useState(false);
  const [xPosition, setX] = useState(width);
  const side = useRef(null);

  // button 클릭 시 토글
  const toggleMenu = () => {
    if (xPosition > 0) {
      setX(0);
      setOpen(true);
    } else {
      setX(width);
      setOpen(false);
    }
  };

  // 사이드바 외부 클릭시 닫히는 함수
  const handleClose = async (e) => {
    let sideArea = side.current;
    let sideCildren = side.current?.contains(e.target);
    if (isOpen && (!sideArea || !sideCildren)) {
      await setX(width);
      await setOpen(false);
    }
  };
  useEffect(() => {
    window.addEventListener("click", handleClose);
    return () => {
      window.removeEventListener("click", handleClose);
    };
  });

  return (
    <div className={styles.container}>
      <div
        ref={side}
        className={styles.sidebar}
        style={{
          width: `${width}px`,
          height: "100%",
          transform: `translatex(${-xPosition}px)`,
        }}
      >
        <div className={styles.content}>
          <Lside />
        </div>
        <button onClick={() => toggleMenu()} className={styles.button}>
          {isOpen ? (
            <span style={{ fontSize: "20px" }}>X</span>
          ) : (
            <ArrowBigRightDash style={{ pointerEvents: "none" }} />
          )}
        </button>
      </div>
    </div>
  );
};

export default Sidebar;
