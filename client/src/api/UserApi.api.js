import { request } from "../helper/request";
export class UserApi {
  static fetchAll = () => {
    return request({
      method: "GET",
      url: `/api/user`,
    });
  };

  static getMe = () => {
    return request({
      method: "GET",
      url: `/api/user-token/get-me`,
    });
  };
}
