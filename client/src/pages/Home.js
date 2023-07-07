import { getUserCurrent } from "../helper/UserCurrent";

const Home = () => {
  const userCurrent = getUserCurrent();
  return (
    <div>
      <h1>Đây là trang chủ</h1>
      <h2>{userCurrent.fullName}</h2>
      <h2>{userCurrent.email}</h2>
    </div>
  );
};

export default Home;
