import { useState } from "react";
import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import userImgg from "../../assets/user.png";

export default function Layout() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const visibleAvatars = Array(3).fill({ id: 1, name: "User" });
  const extraCount = 5;

  return (
    <div className="flex">
      <Sidebar
        isOpen={isSidebarOpen}
        toggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)}
      />

      <main
        className={`bg-gray-100 min-h-screen transition-all duration-300 ${
          isSidebarOpen ? "md:ml-64" : "md:ml-20"
        }`}
      >
        {/* Header */}
        <div className=" bg-[#022639] text-white px-4 py-3 flex flex-col sm:flex-row items-center justify-between gap-4 shadow-sm flex-wrap">
          <div className="flex justify-center sm:justify-start flex-1">
            <div className="flex -space-x-2 h-8"></div>
          </div>
        </div>

        <Outlet />
      </main>
    </div>
  );
}
