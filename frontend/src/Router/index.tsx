import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import {
  LayoutHome,
  LayoutLogin,
  Home,
  PageNotFound,
  Login,
  Learn,
  Profile,
  Register,
  ResetPass,
  ProjectsLayout,
  Projects,
  SessionRequests,
} from "../imports";
export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<LayoutHome />}>
        <Route index element={<Home />} />
        <Route path="Learn" element={<Learn />} />
        <Route path="Projects" element={<ProjectsLayout />}>
          <Route index element={<Projects />} />
          <Route path="SessionRequests" element={<SessionRequests />} />
        </Route>
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
