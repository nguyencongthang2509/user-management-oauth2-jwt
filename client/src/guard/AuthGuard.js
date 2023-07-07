import { useEffect } from "react";
import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
import { getToken } from "../helper/UserCurrent";

const AuthGuard = ({ children, levels }) => {
  const token = getToken();
  const navigate = useNavigate();

  const getMe = () => {
    UserApi.getMe().then((response) => {
      localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
    });
  };

  useEffect(() => {
    getMe();
  }, []);

  if (token == null) {
    navigate("/login");
    return;
  }

  return children;
};

export default AuthGuard;
