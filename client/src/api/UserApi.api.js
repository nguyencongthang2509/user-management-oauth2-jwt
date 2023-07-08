import { request } from "../helper/request";
export class UserApi {
  static fetchAll = () => {
    return request({
      method: "GET",
      url: `/api/mentor/user`,
    });
  };

  static getProfile = (id) => {
    return request({
      method: "GET",
      url: `/api/intern/user/` + id,
    });
  };

  static getMe = () => {
    return request({
      method: "GET",
      url: `/api/user-token/get-me`,
    });
  };

  static logOut = () => {
    return request({
      method: "POST",
      url: `/api/authentication/logout`,
    });
  };

  static register = (data) => {
    return request({
      method: "POST",
      url: `/api/authentication/register`,
      data: data,
    });
  };
}
