import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getToken } from "../helper/UserCurrent";
import jwtDecode from "jwt-decode";

const GuestGuard = ({ children }) => {
  const userToken = getToken();
  const navigate = useNavigate();

  useEffect(() => {
    if (userToken === "undefined" || userToken === null) {
      return children;
    }

    const decodedToken = jwtDecode(userToken);
    const currentTime = Date.now() / 1000;

    if (decodedToken.exp * 1000 > currentTime * 1000) {
      navigate("/home");
    } else {
      return children;
    }
  }, []);
};

export default GuestGuard;
