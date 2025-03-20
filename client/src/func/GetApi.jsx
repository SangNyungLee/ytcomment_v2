import axios from "axios";
//댓글 가져오기
const fetchComments = async (videoId, commentNumber, token) => {
  try {
    const res = await axios.get(
      "https://www.googleapis.com/youtube/v3/commentThreads",
      {
        params: {
          key: import.meta.env.VITE_APIKEY,
          part: "snippet",
          videoId: videoId,
          maxResults: commentNumber,
          order: "relevance",
          pageToken: token,
        },
      }
    );
    return res.data;
  } catch (error) {
    console.error("댓글오류");
    return null;
  }
};

// 통계값 가져오기
const getStatistics = async (videoId) => {
  try {
    const res = await axios.get(
      "https://www.googleapis.com/youtube/v3/videos",
      {
        params: {
          key: import.meta.env.VITE_APIKEY,
          part: "statistics",
          id: videoId,
        },
      }
    );
    // 응답 처리
    return res.data;
  } catch (error) {
    console.error(error);
  }
};

//검색어 입력받은거 가져오기
const searchYoutubeVideos = async (query, pageToken) => {
  try {
    const result = await axios.get(
      "https://www.googleapis.com/youtube/v3/search",
      {
        params: {
          key: import.meta.env.VITE_APIKEY,
          type: "video",
          part: "snippet",
          q: query, //query가 검색부분임
          maxResults: 8,
          pageToken: pageToken,
        },
      }
    );
    return result;
  } catch (error) {
    console.error("검색에러", error);
  }
};
//댓글 가져오기
//글자수 제한 함수
function truncateText(text) {
  if (text.length <= 50) {
    return text;
  } else {
    const sliceText = text.slice(0, 50) + "...";
    return sliceText;
  }
}
function getCookie(key, data, username) {
  const token = data;
  // 만료날짜 설정
  const expires = new Date();
  expires.setTime(expires.getTime() + 2 * 60 * 60 * 1000);
  // 쿠키 설정 (path 설정 = 쿠키가 적용되는 경로지정)
  document.cookie = `${key} = ${token}; expires=${expires.toUTCString()} path=/`;
  document.cookie = `username = ${username}; expires=${expires.toUTCString()} path=/`;
  window.location.href = "/";
}
function deleteCookie() {
  document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}
export {
  fetchComments,
  truncateText,
  searchYoutubeVideos,
  getCookie,
  getStatistics,
  deleteCookie,
};
