// 시간 변경해주는 함수
const formatPublishedAt = (publishedAt) => {
    const date = new Date(publishedAt);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  export default formatPublishedAt;