import { useEffect, useState } from "react";
import { ThumbsUp, MessageSquare, Share2, Bookmark } from "lucide-react";
import '../css/ScrapContent.css';

export default function VideoScrapingPage ({scrapId}) {
	const [loading, setLoading] = useState(true);
	const [items, setItems] = useState([]);

	useEffect(() => {
		setLoading(true);
		setTimeout(() => {
		  const mockData = Array.from({ length: 6 }, (_, i) => ({
			id: `item-${i}`,
			title: scrapId === "my-channel" ? `인기 유튜브 채널 ${i + 1}` : `스크랩한 영상 제목 ${i + 1}`,
			channel: `채널 ${String.fromCharCode(65 + i)}`,
			thumbnail: null,
			views: `${Math.floor(Math.random() * 100) + 1}만`,
			date: `2023.${Math.floor(Math.random() * 12) + 1}.${Math.floor(Math.random() * 28) + 1}`,
			category: ["음악", "게임", "요리", "여행", "스포츠", "교육"][Math.floor(Math.random() * 6)],
		  }));
		  setItems(mockData);
		  setLoading(false);
		}, 800);
	  }, [scrapId]);


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
					{item.thumbnail ? (
					  <img src={item.thumbnail} alt={item.title} className="scrap-thumbnail" />
					) : (
					  <div className="skeleton skeleton-thumbnail"></div>
					)}
					<div className="scrap-info">
					  <p className="scrap-category">{item.category}</p>
					  <h3 className="scrap-title">{item.title}</h3>
					  <p className="scrap-meta">
						{item.channel} • {item.views}회 • {item.date}
					  </p>
					</div>
	
					{/* ✅ 좋아요, 댓글, 공유, 스크랩 아이콘 추가 */}
					<div className="scrap-footer">
					  <button className="icon-btn">
						<ThumbsUp className="icon" />
						<span>{item.likes}</span>
					  </button>
					  <button className="icon-btn">
						<MessageSquare className="icon" />
						<span>{item.comments}</span>
					  </button>
					  <button className="icon-btn">
						<Share2 className="icon" />
					  </button>
					  <button className="icon-btn">
						<Bookmark className="icon" />
					  </button>
					</div>
				  </div>
				))}
		  </div>
		</div>
	  );
}