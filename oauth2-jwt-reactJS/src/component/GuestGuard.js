import { Navigate } from "react-router";
import { getToken } from "../helper/UserCurrent";

const GuestGuard = ({ children }) => {
  const userToken = getToken();
  if (!userToken || userToken === "" || userToken == null) {
    return children;
  }

  return <Navigate to="/home" />;
};

export default GuestGuard;
