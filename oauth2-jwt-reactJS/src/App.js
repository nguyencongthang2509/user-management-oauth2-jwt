import "./App.css";
import AuthGuard from "./component/AuthGuard";
import DashBoardIntern from "./pages/DashBoardIntern";
import DashBoardMentor from "./pages/DashBoardMentor";
import Login from "./pages/Login";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import SingUp from "./pages/Singup";
import NotAuthorizationComponent from "./pages/403/403";
import Home from "./pages/Home";
import GuestGuard from "./component/GuestGuard";

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
