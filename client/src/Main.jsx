import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, Col, Row } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/Main.css";
import Spinner from "react-bootstrap/Spinner";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { BsYoutube, BsFillPinFill } from "react-icons/bs";
import { truncateText } from "./func/GetApi";
import "./css/Pagination.css";
import Pagination from "react-js-pagination";

export default function Main() {
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [page, setPage] = useState(1);
  const [totalItems, setTotalItems] = useState(0);
  const newCategory = useSelector((state) => state.category.category);

  const handlePageChange = (page) => {
    setPage(page);
  };

  const fetchVideos = async (page, newCategory) => {
    setLoading(true);
    try {
      const res = await axios.post("http://localhost:8080/api/trending", {
        page,
        newCategory,
      });
      const newVideos = res.data;
      console.log("trending video에서 받아온 데이터(res) : ", res);
      setVideos([...newVideos]);
    } catch (error) {
      if (error) {
        console.error("에러입니다.", error);
      }
    }
    setLoading(false);
  };

  useEffect(() => {
    axios.get("http://localhost:8080/api/totalPage").then((res) => {
      setTotalItems(res.data.totalPage);
    });
  }, []);

  useEffect(() => {
    window.scrollTo(0, 0);
    fetchVideos(page, newCategory);
  }, [newCategory, page]);

  return (
    <div className="text-center">
      <Row className="justify-content-center" style={{ width: "100%" }}>
        {videos.map((video) => (
          <Col xs={7} sm={7} md={5} lg={4} xl={3} xxl={2} key={video.id}>
            <Card style={{ width: "100%", marginBottom: "20px" }}>
              {selectedVideo === video.id ? (
                <iframe
                  id={`${video.id}`}
                  width="100%"
                  height="250px"
                  src={`https://www.youtube.com/embed/${video.id}`}
                  frameBorder="0"
                  allowFullScreen
                  title="YouTube Video"
                />
              ) : (
                <Card.Img
                  variant="top"
                  src={video.thumbnails}
                  onClick={() => setSelectedVideo(video.id)}
                />
              )}
              <Card.Body>
                <Card.Title>{video.channelTitle}</Card.Title>
                <Card.Text className="cardText">{video.title}</Card.Text>
                <div
                  style={{
                    color: "gray",
                    marginBottom: "10px",
                    whiteSpace: "pre-line",
                  }}
                >
                  {truncateText(video.description)
                    .split("\\n")
                    .map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
                </div>
                <div>
                  <div>
                    <div key={video.idx}>
                      {video.textOriginal && (
                        <div className="commentStyle">
                          <div style={{ marginBottom: "5px" }}>
                            <span style={{ marginRight: "3px" }}>👍</span>
                            {video.likeCount}
                          </div>
                          {truncateText(video.textOriginal)}
                        </div>
                      )}
                    </div>
                    <div className="button-container">
                      <Link
                        to="/page"
                        state={{ data: video }}
                        className="btn moreBtn"
                      >
                        <BsYoutube className="btnIcon" />
                        더보기
                      </Link>
                      <button className="btn clipBtn">
                        <BsFillPinFill className="btnIcon" />
                        스크랩
                      </button>
                    </div>
                  </div>
                </div>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {loading && <Spinner animation="border" />}
      <Pagination
        activePage={page}
        itemsCountPerPage={12}
        totalItemsCount={totalItems}
        pageRangeDisplayed={10}
        prevPageText={"‹"}
        nextPageText={"›"}
        onChange={handlePageChange}
      />
    </div>
  );
}