import { Link } from "react-router-dom";
import page404 from "../../assets/404.svg";
import { IoHomeOutline } from "react-icons/io5";
const PageNotFound = () => {
  return (
    <main className="flex min-h-screen items-center justify-center bg-white -my-25  ">
      <div className="text-center min-w-2xl">
        <img src={page404} alt="" />

        <div className=" flex items-center justify-center gap-x-6">
          <Link
            to="/"
            className="rounded-xl flex items-center   bg-green-400  focus:bg-green-600  px-3.5 py-2.5 text-xl font-semibold text-white"
          >
            <IoHomeOutline className="mr-2 h-4 w-4" /> Home
          </Link>
        </div>
      </div>
    </main>
  );
};

export default PageNotFound;
