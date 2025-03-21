import { deleteCookie } from "./GetApi";

const logout = () => {
    deleteCookie();
    sessionStorage.removeItem("userName");
    window.location.replace("/");
  };

export default logout;