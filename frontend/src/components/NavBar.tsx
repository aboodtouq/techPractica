import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { NavLinks } from "../Router/route";
import logo from "/src/assets/white.png";
import "../App.css";
import { CiMenuBurger, CiMenuFries } from "react-icons/ci";
import CookiesService from "../service.ts";

function Navbar() {
  const token = CookiesService.get("jwt");
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const RenderNavLinks = NavLinks.filter(({ label }) => {
    if (token && (label === "Join" || label === "Login")) {
      return false;
    }
    return true;
  }).map(({ label, path }) => (
    <NavLink key={label} to={path} className="px-4">
      {label}
    </NavLink>
  ));
  return (
    <nav className=" bg-black text-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center space-x-2">
            <img src={logo} className="h-32 w-32" />
          </div>
          <div className="md:hidden">
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="text-white focus:outline-none text-2xl"
            >
              {isOpen ? <CiMenuFries /> : <CiMenuBurger />}
            </button>
          </div>

          {/* Desktop Menu */}
          <div className="hidden md:flex space-x-12 text-sm ">
            {RenderNavLinks}
            {token ? (
              <NavLink
                to={""}
                onClick={() => {
                  CookiesService.remove("jwt");
                  navigate("/");
                }}
              >
                Logout
              </NavLink>
            ) : (
              ""
            )}
          </div>
        </div>

        {/* Mobile Menu with Transition */}
        <div
          className={`md:hidden overflow-hidden transition-all duration-400 ease-in-out transform flex flex-col items-center space-x-8 ${
            isOpen
              ? "max-h-60 opacity-100 translate-y-0"
              : "max-h-0 opacity-0 -translate-y-2"
          } mt-2 space-y-5 text-sm`}
        >
          {RenderNavLinks}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
