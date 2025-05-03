import { Link } from "react-router-dom";
import page404 from "../../assets/404.svg";
import { IoHomeOutline } from "react-icons/io5";

const PageNotFound = () => {
  return (
    <main className="min-h-screen flex flex-col items-center justify-center bg-white p-6">
      <div className="max-w-md text-center space-y-8">
        <img
          src={page404}
          alt="404 Not Found"
          className="w-full max-w-xs mx-auto"
        />

        <div className="space-y-4">
          <h1 className="text-3xl font-bold text-[#022639]">Page Not Found</h1>
          <p className="text-gray-600">
            The page you're looking for doesn't exist or has been moved.
          </p>
        </div>

        <Link
          to="/"
          className="inline-flex items-center justify-center gap-2 rounded-lg bg-[#42D5AE] hover:bg-[#38b28d] focus:ring-2 focus:ring-[#42D5AE] focus:ring-offset-2 text-white font-medium px-5 py-3 transition-colors duration-200"
        >
          <IoHomeOutline className="h-5 w-5" />
          Return Home
        </Link>
      </div>
    </main>
  );
};

export default PageNotFound;
