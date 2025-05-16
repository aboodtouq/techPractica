import { FaUserFriends, FaPlus, FaCommentDots } from "react-icons/fa";
import { MdOutlineSpaceDashboard, MdOutlineViewKanban } from "react-icons/md";
import userImgg from "../../assets/user.png";

export default function Sidebar() {
  return (
    <div className="sticky top-0 left-0 right-0 h-14 bg-[#022639] text-gray-200 flex items-center overflow-hidden z-40 shadow-md mb-7">
      {/* User info - always visible but adjusts spacing */}
      <div className="flex items-center gap-2 sm:gap-3 px-3 sm:px-4 shrink-0 min-w-max">
        <div className="w-7 h-7 sm:w-8 sm:h-8 rounded overflow-hidden flex items-center justify-center">
          <img
            src={userImgg}
            alt="Anonymous User"
            className="w-full h-full object-cover"
          />
        </div>
        <div className="overflow-hidden">
          <div className="font-semibold leading-tight whitespace-nowrap text-xs sm:text-sm">
            Owner Username
          </div>
          <div className="text-xs text-gray-400 whitespace-nowrap">
            Session Name
          </div>
        </div>
      </div>

      {/* Navigation - keeps all labels visible */}
      <nav className="flex-1 flex items-center px-1 sm:px-2 space-x-1 overflow-x-auto min-w-max">
        <button className="flex items-center gap-1 sm:gap-2 h-full px-2 sm:px-3 rounded hover:bg-[#38b28d] transition whitespace-nowrap text-xs sm:text-sm">
          <MdOutlineSpaceDashboard className="text-base sm:text-lg" />
          Dashboard
        </button>

        <button className="flex items-center gap-1 sm:gap-2 h-full px-2 sm:px-3 rounded hover:bg-[#38b28d] transition whitespace-nowrap text-xs sm:text-sm">
          <MdOutlineViewKanban className="text-base sm:text-lg" />
          Kanban
        </button>

        <button className="flex items-center gap-1 sm:gap-2 h-full px-2 sm:px-3 rounded hover:bg-[#38b28d] transition whitespace-nowrap text-xs sm:text-sm">
          <FaCommentDots className="text-base sm:text-lg" />
          Chat
        </button>

        <button className="flex items-center gap-1 sm:gap-2 h-full px-2 sm:px-3 rounded hover:bg-[#38b28d] transition whitespace-nowrap text-xs sm:text-sm">
          <FaUserFriends className="text-base sm:text-lg" />
          Members
          <FaPlus className="text-[0.6rem] sm:text-xs opacity-60 ml-0.5 sm:ml-1" />
        </button>
      </nav>
    </div>
  );
}
