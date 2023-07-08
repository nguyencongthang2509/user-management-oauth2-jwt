import React from "react";
import { GoogleOAuthProvider, GoogleLogin } from "@react-oauth/google";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import FacebookLogin from "react-facebook-login";

const apiUrlLogin = "http://localhost:8080/api/authentication/login";

const Login = () => {
  const navigate = useNavigate();

  const handleGoogleLoginSuccess = (response) => {
    axios
      .post(apiUrlLogin + "-google/" + response.credential)
      .then((res) => {
        localStorage.setItem("userCurrent", JSON.stringify(res.data.data));
        navigate("/home");
      })
      .catch((err) => {
        navigate("/singup");
      });
  };

  const handleGoogleLoginFailure = (error) => {
    console.log(error);
  };

  const onFinish = (values) => {
    console.log(values);
    axios
      .post(apiUrlLogin + "-basic", values)
      .then((res) => {
        localStorage.setItem("userCurrent", JSON.stringify(res.data.data));
        navigate("/home");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const responseFacebook = (response) => {
    console.log(response);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h2>Login</h2>
        <Form className="login-form" onFinish={onFinish}>
          <Form.Item
            name="email"
            rules={[
              { required: true, message: "Please enter your email" },
              { type: "email", message: "Please enter a valid email" },
            ]}
          >
            <Input
              prefix={<UserOutlined className="site-form-item-icon" />}
              placeholder="Email"
            />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[{ required: true, message: "Please enter your password" }]}
          >
            <Input.Password
              prefix={<LockOutlined className="site-form-item-icon" />}
              placeholder="Password"
            />
          </Form.Item>
          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              className="login-form-button"
            >
              Log in
            </Button>
          </Form.Item>
        </Form>
        <GoogleOAuthProvider clientId="910299676468-6cjt8a57ipkf0hg07gcogvj78b2hmi9t.apps.googleusercontent.com">
          <GoogleLogin
            buttonText="Continue with Google"
            onSuccess={handleGoogleLoginSuccess}
            onFailure={handleGoogleLoginFailure}
            cookiePolicy="single_host_origin"
          />
        </GoogleOAuthProvider>
        <br />
        <FacebookLogin
          appId="100078050940024"
          autoLoad={true}
          fields="name,email,picture"
          scope="public_profile,user_friends"
          callback={responseFacebook}
          icon="fa-facebook"
        />
        <br />
        <Button>
          <Link to="/singup">Sing up</Link>
        </Button>
      </header>
    </div>
  );
};

export default Login;
