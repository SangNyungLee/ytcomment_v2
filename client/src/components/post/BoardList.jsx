import React from "react";

const dummyPosts = [
  {
    id: 1,
    title: "이 영상 진짜 레전드네요",
    content: "보다가 웃겨서 눈물남ㅋㅋ 여러분도 꼭 보세요!",
    author: "홍길동",
    date: "2025-04-01",
    likes: 23,
  },
  {
    id: 2,
    title: "추천 영상 하나 공유합니다",
    content: "이건 진짜 숨겨진 명작이에요. 조회수 낮은 게 아쉬움ㅠ",
    author: "김코딩",
    date: "2025-03-30",
    likes: 45,
  },
  // 더미 데이터 추가 가능
];

const FreeBoardList = () => {
  return (
    <div className="p-6 space-y-4">
      <h2 className="text-2xl font-semibold">자유게시판</h2>
      {dummyPosts.map((post) => (
        <div
          key={post.id}
          className="p-4 bg-white rounded-2xl shadow border hover:shadow-md transition"
        >
          <div className="flex justify-between items-center">
            <h3 className="text-lg font-bold">{post.title}</h3>
            <span className="text-sm text-gray-500">{post.date}</span>
          </div>
          <p className="text-gray-700 mt-2 line-clamp-2">{post.content}</p>
          <div className="flex justify-between items-center mt-3 text-sm text-gray-600">
            <span>작성자: {post.author}</span>
            <span>👍 {post.likes}</span>
          </div>
        </div>
      ))}
    </div>
  );
};

export default FreeBoardList;
