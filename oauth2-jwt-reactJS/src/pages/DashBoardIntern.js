import axios from "axios";
import { useEffect } from "react";
import { apiUrl } from "../constant/Api";
import { getToken } from "../helper/UserCurrent";
import { Navigate, useNavigate } from "react-router";
import { UserApi } from "../api/UserApi.api";
const DashBoardIntern = () => {
  const navigate = useNavigate();
  useEffect(() => {
    loadData();
  });

  const loadData = () => {
    UserApi.fetchAll().then(() => {

    });
  };
  return <div> Intern</div>;
};

export default DashBoardIntern;
