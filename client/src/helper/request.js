import axios from "axios";
import { AppConfig } from "../AppConfig";
import { getToken } from "./UserCurrent";

export const request = axios.create({
  baseURL: AppConfig.apiUrl,
});

request.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

request.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 500) {
      localStorage.removeItem("userCurrent");
      window.location.href = "/login";
    }
  }
);
