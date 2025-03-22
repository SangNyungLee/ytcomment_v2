import axios from "axios";
import Cookies from "js-cookie";

const deleteAccount = async () => {
  const confirmDelete = window.confirm("정말로 회원 탈퇴하시겠습니까?");
  if (!confirmDelete) return;

  try {
    const response = await axios.delete(
      `${import.meta.env.VITE_API_URL}/user/delete`,
      {
        headers: {
          Authorization: `Bearer ${Cookies.get("token")}`,
        },
      }
    );

    if (response.status === 200) {
      alert("회원 탈퇴가 완료되었습니다.");
      Cookies.remove("token");
      Cookies.remove("username");
      window.location.href = "/";
    }
  } catch (error) {
    console.error("회원 탈퇴 실패:", error);
    alert("회원 탈퇴 중 오류가 발생했습니다.");
  }
};

export default deleteAccount;
