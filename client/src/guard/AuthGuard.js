import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
import { useEffect } from "react";

const AuthGuard = ({ children, levels }) => {
  const navigate = useNavigate();
  const authenticateToken = async () => {
    try {
      const response = await UserApi.getMe();
      if (response != null) {
        localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
      }
    } catch (error) {
    }
  };

  useEffect(() => {
    authenticateToken();
  }, []);

  return children;
};

export default AuthGuard;
