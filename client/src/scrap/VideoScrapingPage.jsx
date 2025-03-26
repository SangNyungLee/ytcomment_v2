import { useEffect, useState } from "react";
import { ThumbsUp, MessageSquare, Bookmark } from "lucide-react";
import "../css/ScrapContent.css";
import userScrapData from "./UserScrapData";
import formatNumber from "../func/FormatNumber";
import formatPublishedAt from "../func/FormatPublishedAt";
import UserVideoLike from "./UserLike";
import { Link } from "react-router-dom";
import Pagination from "react-js-pagination";
import getMyTotalPage from "@/func/getMyTotalPage";

const ITEMS_PER_PAGE = 8;

const VideoScrapingPage = ({ scrapId }) => {
  const [loading, setLoading] = useState(true);
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(1);
  const [totalPage, setTotalPage] = useState(0);

  const fetchData = async () => {
    setLoading(true);
    try {
      const data = await userScrapData({ page, size: ITEMS_PER_PAGE });

      setItems(data);
    } catch (error) {
      console.error("데이터 로드 실패", error);
    } finally {
      setLoading(false);
    }
  };

  const fetchTotalPage = async () => {
    try {
      const pageNum = await getMyTotalPage();
      setTotalPage(pageNum);
    } catch (error) {
      console.error("전체 페이지 수 로드 실패", error);
    }
  };

  useEffect(() => {
    fetchTotalPage();
  }, [scrapId]);

  useEffect(() => {
    fetchData();
  }, [page, scrapId]);

  const handleVideoLike = async (videoId) => {
    const result = await UserVideoLike(videoId);
    if (result) {
      fetchData();
    }
  };

  const handlePageChange = (pageNumber) => {
    setPage(pageNumber);
  };

  return (
    <div className="scrap-content">
      <h1 className="scrap-title">
        {scrapId === "my-channel" ? "MY 채널 스크랩" : "MY 영상 스크랩"}
      </h1>
      <div className="scrap-list">
        {loading
          ? Array.from({ length: 6 }).map((_, i) => (
              <div key={i} className="scrap-card">
                <div className="skeleton skeleton-thumbnail"></div>
                <div className="scrap-info">
                  <div className="skeleton skeleton-text"></div>
                  <div className="skeleton skeleton-text short"></div>
                </div>
                <div className="scrap-footer">
                  <div className="skeleton skeleton-icon"></div>
                  <div className="skeleton skeleton-icon"></div>
                  <div className="skeleton skeleton-icon"></div>
                  <div className="skeleton skeleton-icon"></div>
                </div>
              </div>
            ))
          : items.map((item) => (
              <div key={item.id} className="scrap-card">
                <Link to="/page" state={{ videoId: item.id }}>
                  {item.thumbnails ? (
                    <img
                      src={item.thumbnails}
                      alt={item.title}
                      className="scrap-thumbnail"
                    />
                  ) : (
                    <div className="skeleton skeleton-thumbnail"></div>
                  )}
                </Link>
                <div className="scrap-info">
                  <p className="scrap-category">{item.categoryId}</p>
                  <h3 className="scrap-title">{item.title}</h3>
                  <div className="scrap-meta">
                    <span className="scrap-font">{item.channelTitle}</span>
                    <span className="scrap-font2">
                      {formatNumber(item.channelViewCount)}회 •{" "}
                      {formatPublishedAt(item.publishedAt)}
                    </span>
                  </div>
                </div>
                <div className="scrap-footer">
                  <button className="icon-btn">
                    <ThumbsUp className="icon" />
                    <span>{item.channelLikeCount}</span>
                  </button>
                  <button className="icon-btn">
                    <MessageSquare className="icon" />
                    <span>{item.channelCommentCount}</span>
                  </button>
                  <button
                    className="icon-btn"
                    onClick={() => handleVideoLike(item.id)}
                  >
                    <Bookmark className="icon" />
                  </button>
                </div>
              </div>
            ))}
      </div>

      {!loading && totalPage > 1 && (
        <Pagination
          activePage={page}
          itemsCountPerPage={ITEMS_PER_PAGE}
          totalItemsCount={totalPage}
          pageRangeDisplayed={8}
          prevPageText={"‹"}
          nextPageText={"›"}
          onChange={handlePageChange}
        />
      )}
    </div>
  );
};

export default VideoScrapingPage;
