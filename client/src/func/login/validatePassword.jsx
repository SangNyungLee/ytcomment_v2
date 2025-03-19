  // 비밀번호 검증 (영문, 숫자, 특수문자 포함 8자 이상)
  const validatePassword = (password) => {
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(password);
  };

  export default validatePassword;