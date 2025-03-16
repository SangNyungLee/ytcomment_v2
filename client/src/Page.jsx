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
  
  //모달부분
  const [show, setShow] = useState(false);
  const [channelCommentCount, setChannelCommentCount] = useState(0);
  const [channelViewCount, setChannelViewCount] = useState(0);
  const [publishedDate, setPublishedDate] = useState(0);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const location = useLocation();
  const recData = location.state.data;
  const [comment, setComment] = useState([]);
  let tagsArray = [];

  if (recData.tags) {
    tagsArray = recData.tags;
  } else if (typeof recData.tags === "string") {
    tagsArray = recData.tags
      .match(/"([^"]*)"/g)
      .map((tag) => tag.replace(/"/g, ""));
  }

  //클립 버튼 눌렀을 때 복사
  const getUrl = () => {
  };

  useEffect(() => {
    try {
      axios
        .post("http://localhost:8080/api/getPageStatistics", {id : recData.id})
        .then((res) => {
          setChannelCommentCount(res.data.channelCommentCount);
          setChannelViewCount(res.data.channelViewCount);
          setPublishedDate(res.data.publishedAt);
        });
      axios
        .post("http://localhost:8080/api/getPageComment", {id : recData.id})
        .then((res) => {
          const newComments = res.data.map((ment) => {
            return {
              authorName: ment.authorName,
              text: ment.textOriginal,
              like: ment.likeCount,
              time: formatPublishedAt(ment.publishedAt),
              imgUrl: ment.authorProfileImageUrl,
            };
          });
          setComment(newComments);
        });
    } catch (error) {
      console.error("댓글 불러오기 오류");
    }
  }, []);

  return (
    <>
      <header>
        <h3 className="headTitle">
          <div className="chaennelTitle">{recData.title}</div>
        </h3>
      </header>
      <section>
        <div className="profile_info">
          <span className="channelName">{recData.channelTitle}</span>
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
            title={`recData.snippet.channelTitle`}
            src={`https://www.youtube.com/embed/${recData.id}`}
          ></iframe>
        </div>
        <div className="moreInfo">
          <a
            href={`https://www.youtube.com/watch?v=${recData.id}`}
            className="btn youtubeBtn"
          >
            유튜브에서 보기
          </a>
          <a
            className="btn youtubeInfo"
            href={`https://www.youtube.com/channel/${recData.channelId}`}
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
          <span className="btn youtubeChannelClip" onClick={() => UserVideoLike(recData.id)}>채널 스크랩</span>
        </div>
        {/* <ClipIcons /> */}
        <div className="youtubeDescription">
          {recData.description.split("\\n").map((line, index) => {
            return (
              <React.Fragment key={index}>
                {line}
                <br />
              </React.Fragment>
            );
          })}
        </div>
        <br />
        <div className="hashTags">
          {recData.tags
            ? tagsArray.map((res, index) => (
                <span className="tags btn" key={index}>
                  #{res}
                </span>
              ))
            : null}
        </div>
        <div className="vote">
          <span className="positiveBtn">
            <BsFillHandThumbsUpFill />
            추천 <strong>{recData.likeCount}</strong>
          </span>
          <span className="negativeBtn">
            <BsFillHandThumbsDownFill />
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
                <img src={`${res.imgUrl}`} className="commentImg" />
                <div>
                  <span>{res.authorName}</span> <span>{res.time}</span>
                  <br />
                  <span>{res.text}</span>
                  <br />
                  <span>
                    <BsHandThumbsUp style={{ fontWeight: "bold" }} />{" "}
                    <span style={{ fontSize: "13px", fontWeight: "bold" }}>
                      {res.like}
                    </span>
                  </span>
                  <br />
                  <br />
                </div>
              </div>
            ))}
          </div>
          {/* 모달부분 */}
          <Modal
            show={show}
            onHide={handleClose}
            centered // 이 속성을 추가하여 모달을 화면 가운데로 정렬
          >
            <Modal.Header closeButton>
              <Modal.Title>공유하기</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div>
                <ClipIcons />
              </div>
            </Modal.Body>
            <Modal.Footer style={{ justifyContent: "center" }}>
              <span style={{ border: "2px solid #ddd", padding: "5px" }}>
                <span className="ClipUrl">{`https://www.youtube.com/watch?v=${recData.id}`}</span>

                <CopyToClipboard
                  text={`https://www.youtube.com/watch?v=${recData.id}`}
                  onCopy={() => alert("클립보드에 복사되었습니다.")}
                >
                  <button
                    className="btn"
                    style={{ backgroundColor: "#F55145", marginLeft: "15px" }}
                    onClick={(e) => getUrl(e.target.value)}
                  >
                    <BsPaperclip />
                    복사하기
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
