import { useEffect } from "react";
import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
import { getToken } from "../helper/UserCurrent";

const AuthGuard = ({ children, levels }) => {
  const token = getToken();
  const navigate = useNavigate();

  const authenticateToken = async () => {
    try {
      const response = await UserApi.getMe();
      localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
    } catch (error) {
    }
  };

  useEffect(() => {
    if (token != null) {
      authenticateToken();
    } else {
      navigate("/login");
    }
  }, []);

  return children;
};

export default AuthGuard;
