import React from "react";
import { Form, Input, Button, DatePicker, Select } from "antd";
import {
  UserOutlined,
  MailOutlined,
  LockOutlined,
  PhoneOutlined,
  HomeOutlined,
} from "@ant-design/icons";
import { UserApi } from "../api/UserApi.api";
import { useNavigate } from "react-router";

const { Option } = Select;

const SignUp = () => {
  const navigate = useNavigate();

  const onFinish = (values) => {
    UserApi.register(values).then((response) => {
      alert("Đăng ký thành công");
      navigate("/login");
    });
  };

  return (
    <div>
      <h1>Đăng ký</h1>
      <Form name="signup" onFinish={onFinish}>
        <Form.Item
          name="fullName"
          rules={[
            { required: true, message: "Họ và tên không được để trống" },
            { max: 50, message: "Họ và tên không vượt quá 50 ký tự" },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder="Họ và tên" />
        </Form.Item>
        <Form.Item
          name="email"
          rules={[
            { required: true, message: "Email không được để trống" },
            { type: "email", message: "Email không đúng định dạng" },
            { max: 100, message: "Email không vượt quá 100 ký tự" },
          ]}
        >
          <Input prefix={<MailOutlined />} placeholder="Email" />
        </Form.Item>
        <Form.Item
          name="dateOfBirth"
          rules={[{ required: true, message: "Ngày sinh không được để trống" }]}
        >
          <DatePicker placeholder="Ngày sinh" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            { required: true, message: "Mật khẩu không được để trống" },
            { max: 30, message: "Mật khẩu không vượt quá 30 ký tự" },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Mật khẩu" />
        </Form.Item>
        <Form.Item
          name="phoneNumber"
          rules={[
            { required: true, message: "Số điện thoại không được để trống" },
            { max: 20, message: "Số điện thoại không vượt quá 20 ký tự" },
            {
              pattern: /^(\+84|0)\d{9,10}$/,
              message: "Số điện thoại không đúng định dạng",
            },
          ]}
        >
          <Input prefix={<PhoneOutlined />} placeholder="Số điện thoại" />
        </Form.Item>
        <Form.Item
          name="gender"
          rules={[{ required: true, message: "Vui lòng chọn giới tính" }]}
        >
          <Select placeholder="Giới tính">
            <Option value={true}>Nam</Option>
            <Option value={false}>Nữ</Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="address"
          rules={[
            { required: true, message: "Địa chỉ không được để trống" },
            { max: 200, message: "Địa chỉ không vượt quá 200 ký tự" },
          ]}
        >
          <Input prefix={<HomeOutlined />} placeholder="Địa chỉ" />
        </Form.Item>
        <Form.Item
          name="role"
          rules={[{ required: true, message: "Vui lòng chọn vai trò" }]}
        >
          <Select placeholder="Vai trò">
            <Option value="MENTOR">Mentor</Option>
            <Option value="INTERN">Intern</Option>
          </Select>
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Đăng ký
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignUp;
