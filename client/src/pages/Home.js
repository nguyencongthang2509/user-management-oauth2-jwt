import { Button } from "antd";
import { getUserCurrent } from "../helper/UserCurrent";
import { useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";

const Home = () => {
  const userCurrent = getUserCurrent();
  const navigate = useNavigate();

  const logOut = () => {
    UserApi.logOut().then((response) => {
      localStorage.removeItem("userCurrent");
      navigate("/login");
    });
  };

  return (
    <div>
      <h1>Đây là trang chủ</h1>
      <h2>{userCurrent.fullName}</h2>
      <h2>{userCurrent.email}</h2>
      <Button onClick={logOut}>Đăng xuất</Button>
    </div>
  );
};

export default Home;
