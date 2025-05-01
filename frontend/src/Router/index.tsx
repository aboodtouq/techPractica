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
  CookiesService,
} from "../imports";
import ProtactedRoute from "../components/ProtactedRoute";
import Admin from "../pages/Admin/Admin";
const isAdmin = CookiesService.get("isAdmin");

export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route
        path="/"
        element={
          <ProtactedRoute isAllowed={!isAdmin}>
            <LayoutHome />
          </ProtactedRoute>
        }
      >
        <Route index element={<Home />} />
        <Route path="Learn/:category" element={<Learn />} />
        <Route path="Learn/" element={<Learn />} />
        <Route path="Sessions" element={<ProjectsLayout />}>
          <Route index element={<Projects />} />
          <Route path="SessionRequests" element={<SessionRequests />} />
        </Route>
        <Route path="Profile" element={<Profile />} />
      </Route>
      <Route
        path="/Admin"
        element={
          <ProtactedRoute isAllowed={isAdmin}>
            <Admin />
          </ProtactedRoute>
        }
      />
      <Route path="User" element={<LayoutLogin />}>
        <Route index element={<Login />} />
        <Route path="ResetPassword" element={<ResetPass />}></Route>
        <Route path="Register" element={<Register />} />
      </Route>
      <Route
        path="Profile"
        element={
          <ProtactedRoute isAllowed={!isAdmin}>
            <Profile />{" "}
          </ProtactedRoute>
        }
      />
      <Route path="*" element={<PageNotFound />} />
    </>
  )
);
