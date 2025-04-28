import { NavLink, Outlet } from "react-router-dom";

interface IProps {}
const ProjectsLayout = ({}: IProps) => {
  return (
    <>
      <div className="flex">
        {/* Sidebar */}
        <aside className="h-screen w-64 bg-white border-r border-gray-200 p-6 ">
          <ul className="space-y-2 mt-8 ">
            <NavLink
              end
              to=""
              className={({ isActive }) =>
                `block px-4 py-2 rounded-lg text-sm font-medium ${
                  isActive
                    ? "bg-green-100 text-green-700"
                    : "text-gray-700 hover:bg-gray-100 hover:text-green-600"
                }`
              }
            >
              Projects
            </NavLink>
            <NavLink
              to="SessionRequests"
              className={({ isActive }) =>
                `block px-4 py-2 rounded-lg text-sm font-medium ${
                  isActive
                    ? "bg-green-100 text-green-700"
                    : "text-gray-700 hover:bg-gray-100 hover:text-green-600"
                }`
              }
            >
              Session Requests
            </NavLink>
          </ul>
        </aside>

        {/* Main content */}
        <main className="flex-1 p-6 bg-gray-50 min-h-screen">
          <Outlet />
        </main>
      </div>
    </>
  );
};
export default ProjectsLayout;
