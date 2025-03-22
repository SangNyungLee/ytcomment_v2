import { useState } from "react";

const PasswordEditForm = ({ onClose }) => {
  const [form, setForm] = useState({
    currentPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = () => {
    const { currentPassword, newPassword, confirmPassword } = form;

    if (!currentPassword || !newPassword || !confirmPassword) {
      alert("모든 항목을 입력해주세요.");
      return;
    }

    if (newPassword !== confirmPassword) {
      alert("새 비밀번호가 일치하지 않습니다.");
      return;
    }

    // TODO: API 연동
    alert("비밀번호 수정 완료!");

    setForm({
      currentPassword: "",
      newPassword: "",
      confirmPassword: "",
    });

    onClose?.(); // 부모에게 닫기 알림
  };

  return (
    <div className="w-full max-w-md mt-4 p-4 border rounded-lg bg-gray-50">
      <div className="mb-2">
        <label className="block text-sm font-medium mb-1">현재 비밀번호</label>
        <input
          type="password"
          name="currentPassword"
          value={form.currentPassword}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
      </div>
      <div className="mb-2">
        <label className="block text-sm font-medium mb-1">새 비밀번호</label>
        <input
          type="password"
          name="newPassword"
          value={form.newPassword}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
      </div>
      <div className="mb-3">
        <label className="block text-sm font-medium mb-1">비밀번호 확인</label>
        <input
          type="password"
          name="confirmPassword"
          value={form.confirmPassword}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
      </div>
      <div className="flex justify-between">
        <button
          onClick={handleSubmit}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
        >
          비밀번호 수정
        </button>
        <button
          onClick={onClose}
          className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
        >
          취소
        </button>
      </div>
    </div>
  );
};

export default PasswordEditForm;
