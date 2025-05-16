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
import ProtactedRouteUser from "../components/ProtactedRouteUser";
import BorderLayout from "../components/Board/BorderLayout";
import KanbanBoard from "../components/Board/KanbanBoard";
const token = CookiesService.get("UserToken");

export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<LayoutHome />}>
        <Route index element={<Home />} />
        <Route path="Learn" element={<Learn />} />
        <Route path="Learn/:category" element={<Learn />} />
        <Route path="Sessions" element={<ProjectsLayout />}>
          <Route index element={<Projects />} />
        </Route>
        <Route path="/Requests/:id" element={<SessionRequests />} />
        <Route path="Profile" element={<Profile />} />
      </Route>

      <Route path="User" element={<LayoutLogin />}>
        <Route
          index
          element={
            <ProtactedRouteUser token={token} redirectPath="/">
              <Login />
            </ProtactedRouteUser>
          }
        />
        <Route
          path="ResetPassword"
          element={
            <ProtactedRouteUser token={token} redirectPath="/">
              <ResetPass />
            </ProtactedRouteUser>
          }
        />
        <Route
          path="Register"
          element={
            <ProtactedRouteUser token={token} redirectPath="/">
              <Register />
            </ProtactedRouteUser>
          }
        />
      </Route>
      <Route path="SessionBoard" element={<BorderLayout />}>
        <Route index element={<KanbanBoard />} />
      </Route>
      <Route path="*" element={<PageNotFound />} />
    </>
  )
);
