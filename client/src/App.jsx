import "@fontsource/open-sans"; // Defaults to weight 400
import "@fontsource/open-sans/400-italic.css"; // Specify weight and style
import "@fontsource/open-sans/400.css"; // Specify weight
import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./css/App.css";
import Lside from "./LSide";
import Main from "./Main";
import NavBar from "./NavBar";
import Page from "./Page";
import Search from "./Search";
import SearchPage from "./SearchPage";
import Sidebar from "./SideBar";
import Signup from "./Signup";
import SignupForm from "./SignupForm";
import KakaoRedirectHandler from "./func/KakaoRedirectHandler";
function App() {
  // 초기 상태를 window.innerWidth로 설정
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  useEffect(() => {
    const handleResize = () => {
      // 리사이즈 이벤트 발생 시 window.innerWidth 값을 업데이트
      setWindowWidth(window.innerWidth);
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <div className="myContainer">
      <BrowserRouter>
        <div>
          {windowWidth > 991 && <Lside />}
          {windowWidth <= 991 && <Sidebar />}
        </div>
        <div>
          <NavBar />
          <Routes>
            <Route path="/nav" element={<NavBar />} />
            <Route path="/" element={<Main />} />
            <Route path="/search" element={<Search />} />
            <Route path="/page" element={<Page />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/signupForm" element={<SignupForm />} />
            <Route path="/searchPage" element={<SearchPage />} />
            <Route path="/redirectPage" element={<KakaoRedirectHandler/>}/>
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
