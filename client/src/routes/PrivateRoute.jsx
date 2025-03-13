import { Navigate, Outlet } from "react-router-dom";
import Cookies from "js-cookie";
import { useEffect, useState } from "react";
const PrivateRoute = () => {
    const token = Cookies.get("token");
    const [isAuthenticated, setIsAuthenticated] = useState(!!token); //느낌표 2개 쓰면 boolean값으로 변경

    useEffect(() => {
        if (!token){
            alert("로그인한 사용자만 접근 가능한 페이지 입니다.");
        }
    }, [token])
    return isAuthenticated ? <Outlet/> : <Navigate to="/signup" replace/>
};

export default PrivateRoute;