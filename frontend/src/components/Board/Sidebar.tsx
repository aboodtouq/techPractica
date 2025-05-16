import { FaUserFriends, FaPlus, FaCommentDots } from "react-icons/fa";
import { MdOutlineSpaceDashboard, MdOutlineViewKanban } from "react-icons/md";
import userImgg from "../../assets/user.png";

interface SidebarProps {
  isOpen: boolean;
  toggleSidebar: () => void;
}

export default function Sidebar({ isOpen, toggleSidebar }: SidebarProps) {
  return (
    <aside
      className={`fixed hidden md:block left-0 top-0 h-screen bg-[#022639] text-gray-200 flex flex-col overflow-y-auto z-40 transition-all duration-300 ${
        isOpen ? "w-64" : "w-20"
      }`}
    >
      {/* Header with toggle button */}
      <div
        className="flex items-center gap-3 px-4 py-5 cursor-pointer  transition"
        onClick={toggleSidebar}
      >
        <div className="w-10 h-10 rounded overflow-hidden flex items-center justify-center shrink-0">
          <img
            src={userImgg}
            alt="Anonymous User"
            className="w-full h-full object-cover"
          />
        </div>
        {isOpen && (
          <div className="overflow-hidden">
            <div className="font-semibold leading-tight whitespace-nowrap">
              Owner Username
            </div>
            <div className="text-xs text-gray-400 whitespace-nowrap">
              Session Name
            </div>
          </div>
        )}
      </div>

      {/* Navigation */}
      <nav className="flex-1 px-2 py-4 space-y-1">
        <button className="flex items-center gap-3 w-full px-3 py-2 rounded hover:bg-[#38b28d] transition whitespace-nowrap">
          <MdOutlineSpaceDashboard className="text-lg shrink-0" />
          {isOpen && "Dashboard"}
        </button>

        <button className="flex items-center gap-3 w-full px-3 py-2 rounded hover:bg-[#38b28d] transition whitespace-nowrap">
          <MdOutlineViewKanban className="text-lg shrink-0" />
          {isOpen && "Kanban"}
        </button>

        <button className="flex items-center gap-3 w-full px-3 py-2 rounded hover:bg-[#38b28d] transition whitespace-nowrap">
          <FaCommentDots className="text-lg shrink-0" />
          {isOpen && "Chat"}
        </button>

        <button className="flex items-center gap-3 w-full px-3 py-2 rounded hover:bg-[#38b28d] transition whitespace-nowrap">
          <FaUserFriends className="text-lg shrink-0" />
          {isOpen && (
            <>
              <span className="flex-1 text-left">Members</span>
              <FaPlus className="text-xs opacity-60 shrink-0" />
            </>
          )}
        </button>
      </nav>
    </aside>
  );
}
