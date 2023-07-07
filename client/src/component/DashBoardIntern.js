import { useEffect } from "react";
import { UserApi } from "../api/UserApi.api";
const DashBoardIntern = () => {
  useEffect(() => {
    loadData();
  });

  const loadData = () => {
    UserApi.fetchAll().then((response) => {});
  };
  return <div> Intern</div>;
};

export default DashBoardIntern;
