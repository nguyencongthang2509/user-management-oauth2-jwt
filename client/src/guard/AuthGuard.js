import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserApi } from "../api/UserApi.api";
import { getToken } from "../helper/UserCurrent";

const AuthGuard = ({ children }) => {
  const token = getToken();
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        const response = await UserApi.getMe();
        localStorage.setItem("userCurrent", JSON.stringify(response.data.data));
      } catch (error) {}
    })();

    if (token == null) {
      navigate("/login");
    }
  } );

  return children;
};

export default AuthGuard;
