import { useEffect } from "react";
import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
import { getToken } from "../helper/UserCurrent";

const AuthGuard = ({ children, levels }) => {
  const token = getToken();
  const navigate = useNavigate();

  useEffect(() => {
    if (token != null) {
      UserApi.getMe().then((response) => {
        localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
      });
    } else {
      navigate("/login");
    }
  }, []);

  return children;
};

export default AuthGuard;
