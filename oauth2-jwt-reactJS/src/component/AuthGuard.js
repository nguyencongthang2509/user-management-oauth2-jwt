import { getToken } from "@chakra-ui/react";
import { useNavigate } from "react-router";

const AuthGuard = ({ children, levels }) => {
  const token = getToken();
  const navigate = useNavigate();

  //call api để xác thực token xem còn hiệu lực ko
  if (token == null) {
    navigate("/not-found");
  }
  return children;
};

export default AuthGuard;
