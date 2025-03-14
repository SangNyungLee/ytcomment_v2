import { useEffect, useState } from "react";
import { ThumbsUp, MessageSquare, Share2, Bookmark } from "lucide-react";
import '../css/ScrapContent.css';
import userScrapData from "./UserScrapData";
import formatNumber from "../func/FormatNumber";
import formatPublishedAt from "../func/FormatPublishedAt";
import UserVideoLike from "./UserLike";

export default function VideoScrapingPage ({scrapId}) {
	const [loading, setLoading] = useState(true);
	const [items, setItems] = useState([]);

	const fetchData = async () => {
		setLoading(true);
		try {
			const data = await userScrapData();
			console.log(data);
			setItems(data);
		} catch (error) {
			console.error("데이터 로드 실패", error)
		} finally {
			setLoading(false);
		}
	};
	
	useEffect(() => {
		fetchData();
	  }, [scrapId]);

	  const handleVideoLike = async (videoId) => {
		console.log("핸들부분", videoId);
		const result = await UserVideoLike(videoId);
		if (result){
			fetchData();
		}
	  };
	  return (
		<div className="scrap-content">
		  <h1 className="scrap-title">{scrapId === "my-channel" ? "MY 채널 스크랩" : "MY 영상 스크랩"}</h1>
		  <div className="scrap-list">
			{loading
			  ? Array.from({ length: 6 }).map((_, i) => (
				  <div key={i} className="scrap-card">
					<div className="skeleton skeleton-thumbnail"></div>
					<div className="scrap-info">
					  <div className="skeleton skeleton-text"></div>
					  <div className="skeleton skeleton-text short"></div>
					</div>
					{/* 아이콘 부분 Skeleton 추가 */}
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
					{item.thumbnails ? (
					  <img src={item.thumbnails} alt={item.title} className="scrap-thumbnail" />
					) : (
					  <div className="skeleton skeleton-thumbnail"></div>
					)}
					<div className="scrap-info">
					  <p className="scrap-category">{item.categoryId}</p>
					  <h3 className="scrap-title">{item.title}</h3>
					  <p className="scrap-meta">
						{item.channelTitle} • {formatNumber(item.channelViewCount)}회 • {formatPublishedAt(item.publishedAt)}
					  </p>
					</div>
	
					{/* 좋아요, 댓글, 공유, 스크랩 아이콘*/}
					<div className="scrap-footer">
					  <button className="icon-btn">
						<ThumbsUp className="icon" />
						<span>{item.channelLikeCount}</span>
					  </button>
					  <button className="icon-btn">
						<MessageSquare className="icon" />
						<span>{item.channelCommentCount}</span>
					  </button>
					  <button className="icon-btn">
						<Share2 className="icon" />
					  </button>
					  <button className="icon-btn" onClick={() => handleVideoLike(item.id)}>
						<Bookmark className="icon" />
					  </button>
					</div>
				  </div>
				))}
		  </div>
		</div>
	  );
}