import { useEffect, useState } from "react";
import { UserApi } from "../api/UserApi.api";
import { Table } from "antd";
import { useAppDispatch, useAppSelector } from "../app/hook";
import { GetUsers, SetUsers } from "../app/reducer/UserSlice.reducer";

const DashBoardMentor = () => {
  const dispatch = useAppDispatch();
  const [listUser, setListUser] = useState([]);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    UserApi.fetchAll().then((response) => {
      if (response) {
        dispatch(SetUsers(response.data.data));
        setListUser(response.data.data.data);
      }
    });
  };

  const columns = [
    { title: "Full Name", dataIndex: "fullname", key: "fullname" },
    { title: "Email", dataIndex: "email", key: "email" },
    { title: "Date Of Birth", dataIndex: "dateofbirth", key: "dateofbirth" },
    { title: "Phone Number", dataIndex: "phonenumber", key: "phonenumber" },
    { title: "Address", dataIndex: "address", key: "address" },
  ];

  return (
    <div>
      {" "}
      <h2>Mentor</h2>
      <Table dataSource={listUser} rowKey="id" columns={columns} />
    </div>
  );
};

export default DashBoardMentor;
