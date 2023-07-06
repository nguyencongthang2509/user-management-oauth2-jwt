import React from "react";
import { GoogleOAuthProvider, GoogleLogin } from "@react-oauth/google";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const apiUrl = "http://localhost:8080/api/authentication/login";

const Login = () => {
  const navigate = useNavigate();

  const handleGoogleLoginSuccess = (response) => {
    axios
      .get(apiUrl + "/" + response.credential)
      .then((res) => {
        localStorage.setItem("userCurrent", JSON.stringify(res.data));
        navigate("/intern");
      })
      .catch((err) => {
        navigate("/singup");
      });
  };

  const handleGoogleLoginFailure = (error) => {
    console.log(error);
  };

  return (
    <GoogleOAuthProvider clientId="910299676468-6cjt8a57ipkf0hg07gcogvj78b2hmi9t.apps.googleusercontent.com">
      <div className="App">
        <header className="App-header">
          <GoogleLogin
            buttonText="Continue with Google"
            onSuccess={handleGoogleLoginSuccess}
            onFailure={handleGoogleLoginFailure}
            cookiePolicy="single_host_origin"
          />
        </header>
      </div>
    </GoogleOAuthProvider>
  );
};

export default Login;
