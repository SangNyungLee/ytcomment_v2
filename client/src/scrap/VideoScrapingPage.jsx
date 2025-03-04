import { useEffect, useState } from "react"

export default function VideoScrapingPage (scrapId) {
	const [loading, setLoading] = useState(true);
	const [items, setItems] = useState([]);
	const title = scrapId === "myChannel" ? "MY 채널 스크랩" : "MY 영상 스크랩"
		

	return (
		<div className="text-center">
			<div className="text-red-500 font-bold">Tailwind 적용 확인</div>

		</div>
	)
}