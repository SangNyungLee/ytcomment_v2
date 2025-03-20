import React, { useState, useEffect } from "react";
import { Card, Col, Row } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/Search.css";
import Spinner from "react-bootstrap/Spinner";
import { Link, useSearchParams } from "react-router-dom";
import { BsYoutube, BsFillPinFill } from "react-icons/bs";
import { fetchComments, truncateText, searchYoutubeVideos} from "./func/GetApi";

//API키
export default function Search() {
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [pageToken, setPageToken] = useState("");
  const [commentData, setCommentData] = useState([]);
  const [selectedVideo, setSelectedVideo] = useState(null);

  // URL에서 query값 가져오기
  const [searchParams] = useSearchParams();
  const searchQuery = searchParams.get("query");

  const fetchVideos = async (query, token = "") => {
    if (!query) return; // 검색어가 없으면 실행하지 않음
    setLoading(true);
    try {
      const res = await searchYoutubeVideos(query, token);
      const newVideos = res.data.items;

      //기존에 있는 데이터에 새로운 데이터 같이 넣어줌
      setVideos((prevVideos) => {
        const existingVideoIds = new Set(prevVideos.map(video => video.id.videoId));
        const uniqueVideos = newVideos.filter(video => !existingVideoIds.has(video.id.videoId));
        return [...prevVideos, ...uniqueVideos]; // 중복 없는 새로운 비디오 추가
      });
      setPageToken(res.data.nextPageToken);
    } catch (error) {
      if (error instanceof Error) {
        console.error("에러 발생", error);
      }
    }
    setLoading(false);
  };

  // 검색어가 변경될 때마다 실행
  useEffect(() => {
    if(searchQuery){
      setVideos([]);
      fetchVideos(searchQuery);
    }
  }, [searchQuery]);

  // 동영상 댓글 가져오기
  useEffect(() => {
    async function fetchCommentsForVideos() {
      const comments = {};
      for (const video of videos) {
        const videoId = video.id.videoId;
        const commentInfo = await fetchComments(videoId, 2, "");
        comments[videoId] = commentInfo;
      }
      setCommentData((prevComments) => ({...prevComments, ...comments}));
    }
    if (videos.length > 0) {
      fetchCommentsForVideos();
    }
  }, [videos]);

  // 스크롤 이벤트 = 스크롤이 끝까지 내려가면 추가 데이터 로딩(무한스크롤)
  useEffect(() => {
      const handleScroll= () =>{
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
  },[loading, pageToken, searchQuery])

  return (
    <div className="text-center">
      <h3>{searchQuery ? `"${searchQuery}" 검색결과` : "검색어를 입력하세요."}</h3>
      <Row className="justify-content-center" style={{ width: "100%" }}>
        {videos.map((video) => (
          <Col xs={7} sm={7} md={5} lg={4} xl={3} xxl={2} key={video.id.videoId}>
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
					<Link to="/searchPage" state={{ data: video }} className="erText">
					<Card.Title>{video.snippet.channelTitle}</Card.Title>
					<Card.Text className="cardText">{video.snippet.title}</Card.Text>
					</Link>

					<div style={{ color: "gray", marginBottom: "10px" }}>
					{truncateText(video.snippet.description)}
					</div>

					{commentData[video.id.videoId] && (
					<div>
						<div>
						{commentData[video.id.videoId].items.map((comment) => (
							<div key={comment.id}>
							<div className="commentStyle">
								<div style={{ marginBottom: "5px" }}>
								<span style={{ marginRight: "3px" }}>👍</span>
								{comment.snippet.topLevelComment.snippet.likeCount}
								</div>{" "}
								{comment.snippet.topLevelComment.snippet.textOriginal}
							</div>
							</div>
						))}
						<Link to="/searchPage" state={{ data: video }} className="linkColor">
							<button className="btn moreBtn">
							<BsYoutube className="btnIcon" />
							더보기
							</button>
						</Link>
						<button className="btn clipBtn">
							<BsFillPinFill className="btnIcon" />
							스크랩
						</button>
						</div>
					</div>
					)}
				</Card.Body>
				</Card>
          </Col>
        ))}
      </Row>

      {loading && <Spinner animation="border" />}
    </div>
  );
}
