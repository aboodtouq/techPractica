import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import LayoutHome from "../pages/Home/Layout";
import LayoutLogin from "../pages/User/Layout";
import Home from "../pages/Home/Home";
import PageNotFound from "../pages/User/PageNotFound";
import Login from "../pages/User/Login";
import Learn from "../pages/Home/Learn";
import Projects from "../pages/Home/Projects";
import Profile from "../pages/Home/Profile";
import Register from "../pages/User/Register";
import ResetPass from "../pages/User/ResetPass";
export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<LayoutHome />}>
        <Route index element={<Home />} />
        <Route path="Learn" element={<Learn />} />
        <Route path="Projects" element={<Projects />} />
        <Route path="Profile" element={<Profile />} />
      </Route>
      <Route path="User" element={<LayoutLogin />}>
        <Route index element={<Login />} />
        <Route path="ResetPassword" element={<ResetPass />}></Route>
        <Route path="Register" element={<Register />} />
      </Route>
      <Route path="Profile" element={<Profile />} />
      <Route path="*" element={<PageNotFound />} />
    </>
  )
);
