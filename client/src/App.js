import "./App.css";

import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import GuestGuard from "./guard/GuestGuard";
import Login from "./pages/Login";
import Home from "./pages/Home";
import SingUp from "./pages/Singup";
import AuthGuard from "./guard/AuthGuard";
import DashBoardIntern from "./component/DashBoardIntern";
import DashBoardMentor from "./component/DashBoardMentor";
import NotAuthorizationComponent from "./pages/403/403";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate replace to="/login" />} />
          <Route
            path="/login"
            element={
              <GuestGuard>
                <Login />
              </GuestGuard>
            }
          />
          <Route
            path="/intern"
            element={
              <AuthGuard>
                <DashBoardIntern />
              </AuthGuard>
            }
          />
          <Route
            path="/mentor"
            element={
              <AuthGuard>
                <DashBoardMentor />
              </AuthGuard>
            }
          />

          <Route
            path="/singup"
            element={
              <GuestGuard>
                <SingUp />
              </GuestGuard>
            }
          />
          <Route path="/not-found" element={<NotAuthorizationComponent />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
