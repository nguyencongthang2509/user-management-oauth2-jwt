import jwtDecode from "jwt-decode";

export const getUserCurrent = () => {
  const userCurrent = localStorage.getItem("userCurrent");
  return userCurrent ? JSON.parse(userCurrent) : null;
};

export const getIdUser = () => {
  const userCurrent = getDetailUser();
  return userCurrent?.idUser || null;
};

export const getToken = () => {
  const userCurrent = getUserCurrent();
  return userCurrent?.token || null;
};

export const getDetailUser = () => {
  const token = getToken();
  return token ? jwtDecode(token) : null;
};
