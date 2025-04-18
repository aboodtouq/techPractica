import { Outlet } from "react-router-dom";
import img from "../../assets/code-typing-concept-illustration.avif";
interface IProps {}
const Layout = ({}: IProps) => {
  return (
    <>
      <div className="min-h-screen flex items-center justify-center md:bg-gray-50">
        <div className="grid grid-cols-1 md:grid-cols-2 md:bg-white md:rounded-2xl md:shadow-xl overflow-hidden w-full max-w-6xl">
          <div className="hidden md:flex items-center justify-center ">
            <img src={img} alt="Illustration" />
          </div>
          <div className="p-8 md:p-12">
            <Outlet />
          </div>
        </div>
      </div>
    </>
  );
};
export default Layout;
