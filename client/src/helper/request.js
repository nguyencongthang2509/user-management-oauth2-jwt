import axios from "axios";
import { AppConfig } from "../AppConfig";
import { getToken } from "./UserCurrent";
// import { getToken } from "./userToken";

export const request = axios.create({
  baseURL: AppConfig.apiUrl,
});

request.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);