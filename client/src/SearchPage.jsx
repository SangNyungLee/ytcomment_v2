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
import { fetchComments, getStatistics } from "./func/GetApi";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ClipIcons from "./ClipIcons";
import formatPublishedAt from "./func/FormatPublishedAt";
import formatNumber from "./func/FormatNumber";
export default function SearchPage() {
  const [show, setShow] = useState(false);
  const [commentCount, setCommentCount] = useState(0);
  const [viewCount, setViewCount] = useState(0);
  const [likeCount, setLikeCount] = useState(0);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const location = useLocation();
  const videoData = location.state.data;
  const myId = videoData.id;
  const [comment, setComment] = useState([]);

  //클립 버튼 눌렀을 때 복사되는거
  const getUrl = (e) => {
  };
  useEffect(() => {
    // statistics받아옵시다
    getStatistics(videoData.id.videoId).then((res) => {
      setCommentCount(res.items[0].statistics.commentCount);
      setViewCount(res.items[0].statistics.viewCount);
      setLikeCount(res.items[0].statistics.likeCount);
    });

    fetchComments(videoData.id.videoId, 10, "")
      .then((res) => {
        const newComments = res.items.map((ment) => {
          return {
            authorName: ment.snippet.topLevelComment.snippet.authorDisplayName,
            text: ment.snippet.topLevelComment.snippet.textOriginal,
            like: ment.snippet.topLevelComment.snippet.likeCount,
            time: formatPublishedAt(
              ment.snippet.topLevelComment.snippet.publishedAt
            ),
            imgUrl: ment.snippet.topLevelComment.snippet.authorProfileImageUrl,
          };
        });
        setComment(newComments);
      })
      .catch((error) => {
        console.error("에러", error);
      });
  }, []);

  return (
    <>
      <header>
        <h3 className="headTitle">
          <span className="chaennelTitle">{videoData.snippet.title}</span>
        </h3>
      </header>
      <section>
        <div className="profile_info">
          <span className="channelName">{videoData.snippet.channelTitle}</span>
          <span className="channelComments">댓글 : {commentCount}개 </span>
          <span className="channelViews"> 조회수 : {formatNumber(viewCount)}{" "}
          </span>
          <span className="channelUploadDate">
            {formatPublishedAt(videoData.snippet.publishedAt)}
          </span>
        </div>
      </section>
      <section className="videoSection">
        <div className="videoPlayer">
          <iframe
            className="goVideo"
            title={`videoData.snippet.channelTitle`}
            src={`https://www.youtube.com/embed/${videoData.id.videoId}`}
          ></iframe>
        </div>
        <div className="moreInfo">
          <a
            href={`https://www.youtube.com/watch?v=${videoData.id.videoId}`}
            className="btn youtubeBtn"
          >
            유튜브에서 보기
          </a>
          <a
            className="btn youtubeInfo"
            href={`https://www.youtube.com/channel/${videoData.snippet.channelId}`}
          >
            유튜브 채널 정보
          </a>
          <Button
            className="btn youtubeClip"
            variant="primary"
            onClick={handleShow}
          >
            영상 스크랩
          </Button>
          <span className="btn youtubeChannelClip">채널 스크랩</span>
        </div>
        <div className="youtubeDescription">{videoData.snippet.description}</div>
        <br />
        <div className="hashTags">
          {videoData.snippet.tags
            ? videoData.snippet.tags.map((res, index) => (
                <span key={index} className="tags btn" id={res}>
                  #{res}
                </span>
              ))
            : null}
        </div>
        <div className="vote">
          <span className="positiveBtn">
            <span className="thumbBtn">
              <BsFillHandThumbsUpFill/>
            </span>
            추천 <strong>{likeCount}</strong>
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
            <select style={{ marginBottom: "20px", marginTop: "20px" }}>
              <option>관련성 순</option>
              <option>좋아요 많은 순</option>
              <option>최신순</option>
            </select>
          </div>
          <div className="commentList">
            {comment.map((res, index) => (
              <div className="commentDiv" key={index}>
                <img src={`${res.imgUrl}`} className="commentImg" alt="프로필" />
                <div className="commentContent">
                  <div className="commentHeader">
                    <span className="commentAuthor">{res.authorName}</span>
                    <span className="commentTime">{res.time}</span>
                  </div>
                  <div className="commentText">{res.text}</div>
                  <div className="commentLikes">
                    <BsHandThumbsUp />
                    <span className="likeCount">{res.like}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>

          <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
              <Modal.Title>공유하기</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {/* <ClipIcons /> */}
            </Modal.Body>
            <Modal.Footer style={{ justifyContent: "center" }}>
              <span style={{ border: "2px solid #ddd", padding: "5px" }}>
                <span className="ClipUrl">{`https://www.youtube.com/watch?v=${videoData.id}`}</span>
                <CopyToClipboard text={`https://www.youtube.com/watch?v=${videoData.id.videoId}`} onCopy={() => alert("클립보드에 복사되었습니다.")}>
                  <button className="btn" style={{ backgroundColor: "#F55145", marginLeft: "15px" }}>
                    <BsPaperclip />복사하기
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
