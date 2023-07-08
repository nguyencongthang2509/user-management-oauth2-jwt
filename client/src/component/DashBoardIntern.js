import { useEffect, useState } from "react";
import { UserApi } from "../api/UserApi.api";
import { getDetailUser, getIdUser } from "../helper/UserCurrent";

const DashBoardIntern = () => {
  const [profile, setProfile] = useState({});
  const idUser = getIdUser();

  useEffect(() => {
    if (idUser != null) {
      loadData();
    }
  }, [idUser]);

  const loadData = () => {
    UserApi.getProfile(idUser).then((response) => {
      if (response) {
        setProfile(response.data.data);
      }
    });
  };

  return (
    <div>
      {" "}
      <h2>FullName: {profile.fullName}</h2>
      <h2>Email: {profile.email}</h2>
    </div>
  );
};

export default DashBoardIntern;
