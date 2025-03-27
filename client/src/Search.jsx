import React, { useState, useEffect } from "react";
import { Card, Col, Row, Spinner, Placeholder } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link, useSearchParams } from "react-router-dom";
import { BsYoutube, BsFillPinFill } from "react-icons/bs";
import {
  fetchComments,
  truncateText,
  searchYoutubeVideos,
} from "./func/GetApi";
import UserVideoLike from "./scrap/UserLike";

export default function Search() {
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [pageToken, setPageToken] = useState("");
  const [commentData, setCommentData] = useState({});
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [searchParams] = useSearchParams();
  const searchQuery = searchParams.get("query");

  useEffect(() => {
    if (searchQuery) {
      setVideos([]);
      fetchVideos(searchQuery);
    }
  }, [searchQuery]);

  const fetchVideos = async (query, token = "") => {
    if (!query) return;
    setLoading(true);
    try {
      const res = await searchYoutubeVideos(query, token);
      const newVideos = res.data.items;
      setVideos((prev) => {
        const existingIds = new Set(prev.map((v) => v.id.videoId));
        return [
          ...prev,
          ...newVideos.filter((v) => !existingIds.has(v.id.videoId)),
        ];
      });
      setPageToken(res.data.nextPageToken);
    } catch (error) {
      console.error("에러 발생", error);
    }
    setLoading(false);
  };

  useEffect(() => {
    if (videos.length > 0) {
      async function fetchCommentsForVideos() {
        const newComments = {};
        await Promise.all(
          videos.map(async (video) => {
            const videoId = video.id.videoId;
            newComments[videoId] = await fetchComments(videoId, 2, "");
          })
        );
        setCommentData((prev) => ({ ...prev, ...newComments }));
      }
      fetchCommentsForVideos();
    }
  }, [videos]);

  useEffect(() => {
    const handleScroll = () => {
      if (
        window.innerHeight + document.documentElement.scrollTop >=
        document.documentElement.offsetHeight - 10
      ) {
        if (!loading && pageToken) {
          fetchVideos(searchQuery, pageToken);
        }
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [loading, pageToken, searchQuery]);

  return (
    <div className="text-center">
      <h3>
        {searchQuery ? `"${searchQuery}" 검색결과` : "검색어를 입력하세요."}
      </h3>
      <Row className="justify-content-center" style={{ width: "100%" }}>
        {videos.map((video) => (
          <Col
            xs={7}
            sm={7}
            md={5}
            lg={4}
            xl={3}
            xxl={2}
            key={video.id.videoId}
          >
            <Card style={{ width: "100%", marginBottom: "20px" }}>
              {selectedVideo === video.id.videoId ? (
                <iframe
                  id={`${video.id.videoId}`}
                  width="100%"
                  height="250px"
                  src={`https://www.youtube.com/embed/${video.id.videoId}`}
                  frameBorder="0"
                  allowFullScreen
                  title="YouTube Video"
                />
              ) : (
                <Card.Img
                  variant="top"
                  src={video.snippet.thumbnails.high.url}
                  onClick={() => setSelectedVideo(video.id.videoId)}
                />
              )}
              <Card.Body>
                <Link
                  to="/searchPage"
                  state={{ data: video }}
                  className="erText"
                >
                  <Card.Title>{video.snippet.channelTitle}</Card.Title>
                  <Card.Text className="cardText">
                    {video.snippet.title}
                  </Card.Text>
                </Link>
                <div style={{ color: "gray", marginBottom: "10px" }}>
                  {truncateText(video.snippet.description)}
                </div>
                {commentData[video.id.videoId] ? (
                  <div>
                    {commentData[video.id.videoId].items.map((comment) => (
                      <div key={comment.id} className="commentStyle">
                        <div style={{ marginBottom: "5px" }}>
                          <span style={{ marginRight: "3px" }}>👍</span>
                          {comment.snippet.topLevelComment.snippet.likeCount}
                        </div>
                        {comment.snippet.topLevelComment.snippet.textOriginal}
                      </div>
                    ))}
                  </div>
                ) : (
                  <Placeholder as="p" animation="glow">
                    <Placeholder xs={6} />
                    <Placeholder xs={7} />
                    <Placeholder xs={4} />
                  </Placeholder>
                )}
                <Link
                  to="/searchPage"
                  state={{ data: video }}
                  className="linkColor"
                >
                  <button className="btn moreBtn">
                    <BsYoutube className="btnIcon" /> 더보기
                  </button>
                </Link>
                <button
                  className="btn clipBtn"
                  onClick={() => UserVideoLike(video.id.videoId)}
                >
                  <BsFillPinFill className="btnIcon" /> 스크랩
                </button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
      {loading && <Spinner animation="border" />}
    </div>
  );
}
