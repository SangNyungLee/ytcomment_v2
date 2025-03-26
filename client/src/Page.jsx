import React from "react";
import { useLocation } from "react-router-dom";
import "./css/Page.css";
import {
  BsFillHandThumbsUpFill,
  BsFillHandThumbsDownFill,
  BsHandThumbsUp,
  BsPaperclip,
} from "react-icons/bs";
//클립보드 복사하기
import { CopyToClipboard } from "react-copy-to-clipboard/src";
import { useEffect, useState } from "react";
import Modal from "react-bootstrap/Modal";
import ClipIcons from "./ClipIcons";
import axios from "axios";
import formatNumber from "./func/FormatNumber";
import formatPublishedAt from "./func/FormatPublishedAt";
import UserVideoLike from "./scrap/UserLike";

export default function Page() {
  const [videoData, setVideoData] = useState(null); // videoData 상태로 관리
  const [tagsArray, setTagsArray] = useState([]); // 태그 배열도 상태로 관리
  const [show, setShow] = useState(false);
  const [channelCommentCount, setChannelCommentCount] = useState(0);
  const [channelViewCount, setChannelViewCount] = useState(0);
  const [publishedDate, setPublishedDate] = useState(0);
  const [comment, setComment] = useState([]);
  const [sortOption, setSortOption] = useState("like");

  const location = useLocation();
  const videoId = location.state.videoId; // Main에서 받은 videoId
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  // 데이터 로드 함수
  const fetchData = async () => {
    try {
      // 영상 정보 불러오기
      const videoInfo = await axios.post(`${API_BASE_URL}/api/getTags`, {
        videoId,
      });
      setVideoData(videoInfo.data);

      // 태그 문자열을 쉼표 기준으로 나누기
      if (videoInfo.data.tags) {
        if (Array.isArray(videoInfo.data.tags)) {
          setTagsArray(videoInfo.data.tags);
        } else if (typeof videoInfo.data.tags === "string") {
          setTagsArray(videoInfo.data.tags.split(",").map((tag) => tag.trim()));
        }
      }

      // 통계 데이터 불러오기
      const resStats = await axios.post(
        `${API_BASE_URL}/api/getPageStatistics`,
        { id: videoId }
      );
      setChannelCommentCount(resStats.data.channelCommentCount);
      setChannelViewCount(resStats.data.channelViewCount);
      setPublishedDate(resStats.data.publishedAt);

      // 댓글 데이터 불러오기
      const resComments = await axios.post(
        `${API_BASE_URL}/api/getPageComment`,
        { id: videoId }
      );
      const newComments = resComments.data.map((ment) => ({
        authorDisplayName: ment.authorDisplayName,
        text: ment.textOriginal,
        like: ment.likeCount,
        time: formatPublishedAt(ment.publishedAt),
        originalDate: ment.publishedAt,
        imgUrl: ment.authorProfileImageUrl,
      }));
      setComment(newComments);
    } catch (error) {
      console.error("데이터 불러오기 오류:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [videoId]);

  if (!videoData) {
    return <p>로딩 중...</p>;
  }

  return (
    <>
      <header>
        <h3 className="headTitle">
          <div className="chaennelTitle">{videoData.title}</div>
        </h3>
      </header>
      <section>
        <div className="profile_info">
          <span className="channelName">{videoData.channelTitle}</span>
          <span className="channelComments">
            댓글 : {channelCommentCount}개{" "}
          </span>
          <span className="channelViews">
            조회수 : {formatNumber(channelViewCount)}{" "}
          </span>
          <span className="channelUploadDate">
            {formatPublishedAt(publishedDate)}
          </span>
        </div>
      </section>
      <section className="videoSection">
        <div className="videoPlayer">
          <iframe
            className="goVideo"
            title={videoData.channelTitle}
            src={`https://www.youtube.com/embed/${videoData.id}`}
          ></iframe>
        </div>
        <div className="moreInfo">
          <a
            href={`https://www.youtube.com/watch?v=${videoData.id}`}
            className="btn youtubeBtn"
          >
            유튜브에서 보기
          </a>
          <a
            className="btn youtubeInfo"
            href={`https://www.youtube.com/channel/${videoData.channelId}`}
          >
            유튜브 채널 정보
          </a>
          <button
            className="btn youtubeClip"
            variant="primary"
            onClick={handleShow}
          >
            공유하기
          </button>
          <span
            className="btn youtubeChannelClip"
            onClick={() => UserVideoLike(videoData.id)}
          >
            영상 스크랩
          </span>
        </div>
        <div className="youtubeDescription">
          {videoData.description.split("\\n").map((line, index) => (
            <React.Fragment key={index}>
              {line}
              <br />
            </React.Fragment>
          ))}
        </div>
        <br />
        <div className="hashTags">
          {tagsArray.map((res, index) => (
            <span className="tags btn" key={index}>
              #{res}
            </span>
          ))}
        </div>
        <div className="vote">
          <span className="positiveBtn">
            <span className="thumbBtn">
              <BsFillHandThumbsUpFill />
            </span>
            추천 <strong>{videoData.channelLikeCount}</strong>
          </span>
          <span className="negativeBtn">
            <span className="thumbBtn">
              <BsFillHandThumbsDownFill />
            </span>
            비추천
            <strong>0</strong>
          </span>
        </div>
        <div>
          <div>
            <select
              style={{ marginBottom: "20px", marginTop: "20px" }}
              value={sortOption}
              onChange={(e) => setSortOption(e.target.value)}
            >
              <option value="relevance">관련성 순</option>
              <option value="likes">좋아요 많은 순</option>
              <option value="date">최신순</option>
            </select>
          </div>
          {/*  댓글 부분 */}
          <div className="commentList">
            {comment.length == 0 ? (
              <div className="no-comments">
                {" "}
                댓글을 지원하지 않는 영상입니다.😥
              </div>
            ) : (
              comment
                .sort((a, b) => {
                  if (sortOption === "likes") return b.like - a.like;
                  if (sortOption === "date")
                    return new Date(b.originalDate) - new Date(a.originalDate);
                  return 0;
                })

                .map((res, index) => (
                  <div className="commentDiv" key={index}>
                    <img
                      src={`${res.imgUrl}`}
                      className="commentImg"
                      alt="프로필"
                    />
                    <div className="commentContent">
                      <div className="commentHeader">
                        <span className="commentAuthor">
                          {res.authorDisplayName}
                        </span>
                        <span className="commentTime">{res.time}</span>
                      </div>
                      <div className="commentText">{res.text}</div>
                      <div className="commentLikes">
                        <BsHandThumbsUp />
                        <span className="likeCount">{res.like}</span>
                      </div>
                    </div>
                  </div>
                ))
            )}
          </div>
          {/* 모달 */}
          <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
              <Modal.Title>공유하기</Modal.Title>
            </Modal.Header>
            <Modal.Body>{/* <ClipIcons /> */}</Modal.Body>
            <Modal.Footer style={{ justifyContent: "center" }}>
              <span style={{ border: "2px solid #ddd", padding: "5px" }}>
                <span className="ClipUrl">{`https://www.youtube.com/watch?v=${videoData.id}`}</span>
                <CopyToClipboard
                  text={`https://www.youtube.com/watch?v=${videoData.id}`}
                  onCopy={() => alert("클립보드에 복사되었습니다.")}
                >
                  <button
                    className="btn"
                    style={{ backgroundColor: "#F55145", marginLeft: "15px" }}
                  >
                    <BsPaperclip /> 복사하기
                  </button>
                </CopyToClipboard>
              </span>
            </Modal.Footer>
          </Modal>
        </div>
      </section>
    </>
  );
}
