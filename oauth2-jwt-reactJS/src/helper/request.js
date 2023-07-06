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

// Interceptor để bắt lỗi
request.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    // Xử lý lỗi
    if (error.response) {
      // Lỗi từ phản hồi HTTP
      console.error("Error response:", error.response);
    } else if (error.request) {
      // Không nhận được phản hồi từ máy chủ
      console.error("No response received:", error.request);
    } else {
      // Lỗi khác
      console.error("Error:", error.message);
    }
    return Promise.reject(error);
  }
);
