export const getUserCurrent = () => {
  const userCurrent = localStorage.getItem("userCurrent");
  return userCurrent ? JSON.parse(userCurrent) : null;
};

export const getToken = () => {
  const userCurrent = getUserCurrent();
  return userCurrent ? userCurrent.token : null;
};
