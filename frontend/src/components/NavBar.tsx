import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { NavLinks } from "../Router/route";
import logo from "/src/assets/white.png";
import { CiMenuBurger, CiMenuFries } from "react-icons/ci";
import { FiLogOut, FiChevronDown, FiChevronUp } from "react-icons/fi";
import CookiesService from "../service.ts";
import { motion, AnimatePresence } from "framer-motion";

function Navbar() {
  const token = CookiesService.get("UserToken");
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [projectsOpen, setProjectsOpen] = useState(false);

  const filteredLinks = NavLinks.filter(({ label }) => {
    if (token) {
      return label !== "Login" && label !== "Join";
    } else {
      return label !== "Profile" && label !== "Sessions";
    }
  });

  const handleLogout = () => {
    CookiesService.remove("UserToken");
    navigate("/");
    setIsOpen(false);
  };

  // Projects dropdown items
  const projectsItems = [
    { label: " My Session", path: "/Sessions" },
    { label: "Session Requests", path: "/Sessions/SessionRequests" },
  ];

  return (
    <nav className="bg-[#022639] text-white sticky top-0 z-50 shadow-lg">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-20">
          <div className="flex items-center">
            <img
              onClick={() => navigate("/")}
              src={logo}
              className="h-32 w-32 cursor-pointer"
              alt="Logo"
            />
          </div>

          <div className="hidden md:flex items-center space-x-6">
            {filteredLinks.map(({ label, path }) => {
              if (label === "Sessions") {
                return (
                  <div key={label} className="relative group">
                    <button
                      className="flex items-center px-3 py-2 text-sm font-medium transition-colors text-gray-300 hover:text-white hover:bg-[#42D5AE]/20 rounded-md"
                      onClick={() => setProjectsOpen(!projectsOpen)}
                    >
                      {label}
                      {projectsOpen ? (
                        <FiChevronUp className="ml-1" />
                      ) : (
                        <FiChevronDown className="ml-1" />
                      )}
                    </button>

                    <AnimatePresence>
                      {projectsOpen && (
                        <motion.div
                          initial={{ opacity: 0, y: -10 }}
                          animate={{ opacity: 1, y: 0 }}
                          exit={{ opacity: 0, y: -10 }}
                          transition={{ duration: 0.2 }}
                          className="absolute left-0 mt-2 w-48 bg-white rounded-md shadow-lg z-50"
                        >
                          <div className="py-1">
                            {projectsItems.map((item) => (
                              <NavLink
                                key={item.label}
                                to={item.path}
                                className={({ isActive }) =>
                                  `block px-4 py-2 text-sm ${
                                    isActive
                                      ? "bg-[#42D5AE]/10 text-[#022639]"
                                      : "text-gray-700 hover:bg-[#42D5AE]/20"
                                  }`
                                }
                                onClick={() => setProjectsOpen(false)}
                              >
                                {item.label}
                              </NavLink>
                            ))}
                          </div>
                        </motion.div>
                      )}
                    </AnimatePresence>
                  </div>
                );
              }
              return (
                <NavLink
                  key={label}
                  to={path}
                  className={({ isActive }) =>
                    `px-3 py-2 text-sm font-medium transition-colors ${
                      isActive
                        ? "text-[#42D5AE] border-b-2 border-[#42D5AE]"
                        : "text-gray-300 hover:text-white hover:bg-[#42D5AE]/20 rounded-md"
                    }`
                  }
                >
                  {label}
                </NavLink>
              );
            })}

            {token && (
              <button
                onClick={handleLogout}
                className="flex items-center text-gray-300 hover:text-white px-3 py-2 text-sm font-medium transition-colors hover:bg-[#ef4444]/20 rounded-md"
              >
                <FiLogOut className="mr-2" />
                Logout
              </button>
            )}
          </div>

          <div className="md:hidden">
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="text-white p-2 rounded-md hover:bg-[#42D5AE] focus:outline-none focus:ring-2 focus:ring-[#42D5AE]"
              aria-label="Toggle menu"
            >
              {isOpen ? (
                <CiMenuFries className="w-6 h-6" />
              ) : (
                <CiMenuBurger className="w-6 h-6" />
              )}
            </button>
          </div>
        </div>

        <AnimatePresence>
          {isOpen && (
            <motion.div
              initial={{ opacity: 0, height: 0 }}
              animate={{ opacity: 1, height: "auto" }}
              exit={{ opacity: 0, height: 0 }}
              transition={{ duration: 0.3 }}
              className="md:hidden bg-[#022639] overflow-hidden"
            >
              <div className="px-2 pt-2 pb-4 space-y-2">
                {filteredLinks.map(({ label, path }) => {
                  if (label === "Sessions") {
                    return (
                      <div key={label}>
                        <button
                          onClick={() => setProjectsOpen(!projectsOpen)}
                          className="flex items-center justify-between w-full px-3 py-3 rounded-md text-base font-medium text-gray-300 hover:bg-[#42D5AE]/50 hover:text-white"
                        >
                          <span>{label}</span>
                          {projectsOpen ? <FiChevronUp /> : <FiChevronDown />}
                        </button>

                        <AnimatePresence>
                          {projectsOpen && (
                            <motion.div
                              initial={{ opacity: 0, height: 0 }}
                              animate={{ opacity: 1, height: "auto" }}
                              exit={{ opacity: 0, height: 0 }}
                              transition={{ duration: 0.2 }}
                              className="pl-4 space-y-1 overflow-hidden"
                            >
                              {projectsItems.map((item) => (
                                <NavLink
                                  key={item.label}
                                  to={item.path}
                                  className={({ isActive }) =>
                                    `block px-3 py-2 rounded-md text-base font-medium ${
                                      isActive
                                        ? "bg-[#42D5AE] text-white"
                                        : "text-gray-300 hover:bg-[#42D5AE]/30 hover:text-white"
                                    }`
                                  }
                                  onClick={() => setIsOpen(false)}
                                >
                                  {item.label}
                                </NavLink>
                              ))}
                            </motion.div>
                          )}
                        </AnimatePresence>
                      </div>
                    );
                  }
                  return (
                    <NavLink
                      key={label}
                      to={path}
                      className={({ isActive }) =>
                        `block px-3 py-3 rounded-md text-base font-medium ${
                          isActive
                            ? "bg-[#42D5AE] text-white"
                            : "text-gray-300 hover:bg-[#42D5AE]/50 hover:text-white"
                        }`
                      }
                      onClick={() => setIsOpen(false)}
                    >
                      {label}
                    </NavLink>
                  );
                })}

                {token && (
                  <button
                    onClick={handleLogout}
                    className="w-full flex items-center px-3 py-3 rounded-md text-base font-medium text-gray-300 hover:bg-[#ef4444]/50 hover:text-white"
                  >
                    <FiLogOut className="mr-2" />
                    Logout
                  </button>
                )}
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>
    </nav>
  );
}

export default Navbar;
