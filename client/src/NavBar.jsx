import React, { useState } from "react";
import { Navbar, Nav } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { User, Search, House } from "lucide-react";
import "./css/NavBar.css";
import { changeCategory } from "./store";
import { searchYoutubeVideos } from "./func/GetApi";

function NavBar() {
  const [youtubeSearch, setYoutubeSearch] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleKeyDown = async (e) => {
    if (e.key === "Enter") {
      searchYoutubeVideos(youtubeSearch, " ");
      navigate(`/search?query=${youtubeSearch}`);
      setYoutubeSearch("");
    }
  };

  const navigateToCategory = (category) => {
    dispatch(changeCategory[category]());
    navigate("/");
  };

  return (
    <div>
      <Navbar bg="light" expand="lg" className="mainNavBar">
        <Navbar.Brand href="/">
          <House className="goHome" />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" className="order-0" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto navFlex">
            <Nav.Link onClick={() => navigateToCategory("recent")} className="navborder">
              최신
            </Nav.Link>
            <Nav.Link onClick={() => navigateToCategory("music")} className="navborder">
              음악
            </Nav.Link>
            <Nav.Link onClick={() => navigateToCategory("game")} className="navborder">
              게임
            </Nav.Link>
            <Nav.Link onClick={() => navigateToCategory("animal")} className="navborder">
              동물
            </Nav.Link>
          </Nav>

          <Nav className="mr-auto searchContainer">
            <div className="searchBox">
              <input
                className="searchInput"
                type="text"
                placeholder="검색어 입력"
                value={youtubeSearch}
                onChange={(e) => setYoutubeSearch(e.target.value)}
                onKeyDown={handleKeyDown}
              />
              <Link to={"/search"} state={{ data: youtubeSearch }} className="searchIcons">
                <Search size={20}/>
              </Link>
            </div>
            <Link to={"/myPage"}>
              <User className="userIcon" />
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <div className="navUnderline"></div>
    </div>
  );
}

export default NavBar;
