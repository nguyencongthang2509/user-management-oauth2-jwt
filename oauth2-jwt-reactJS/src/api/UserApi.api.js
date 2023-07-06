import { request } from "../helper/requestr";

export class UserApi {
  static fetchAll = () => {
    return request({
      method: "GET",
      url: `/user`,
    });
  };
}
