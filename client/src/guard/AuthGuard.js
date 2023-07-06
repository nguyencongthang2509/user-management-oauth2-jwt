import { getToken } from "@chakra-ui/react";
import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
import { useEffect } from "react";

const AuthGuard = ({ children, levels }) => {
  const token = getToken();
  const navigate = useNavigate();

  // Xác thực token
  const authenticateToken = async () => {
    try {
      const response = await UserApi.getMe();
      if (response != null) {
        localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
      }
    } catch (error) {
      navigate("/login");
    }
  };

  useEffect(() => {
    // Kiểm tra token và xác thực
    if (token) {
      authenticateToken();
    } else {
      navigate("/login");
    }
  }, [token, navigate]);

  return children;
};

export default AuthGuard;
