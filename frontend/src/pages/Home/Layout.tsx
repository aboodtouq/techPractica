import { Outlet } from "react-router-dom";
import Navbar from "../../components/NavBar";
import Footer from "../../components/Footer";

const Layout = () => {
  return (
    <>
      <div className="flex flex-col min-h-screen">
        <Navbar />
        <div className="flex-grow bg-gray-100">
          <Outlet />
        </div>
        <Footer />
      </div>
    </>
  );
};
export default Layout;
