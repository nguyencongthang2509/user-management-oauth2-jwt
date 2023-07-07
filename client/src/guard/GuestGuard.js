import { Navigate } from "react-router";
import { getToken } from "../helper/UserCurrent";
import jwtDecode from "jwt-decode";

const GuestGuard = ({ children }) => {
  const userToken = getToken();
  if (!userToken || userToken === "" || userToken == null) {
    return children;
  }

  const decodedToken = jwtDecode(userToken);
  const currentTime = Date.now() / 1000; 

  if (decodedToken.exp < currentTime) {
    return children; 
  } else {
    return <Navigate to="/login" />; 
  }
};

export default GuestGuard;
