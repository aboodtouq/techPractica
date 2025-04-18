import { useLocation, Link } from "react-router-dom";
import { FaChevronRight } from "react-icons/fa";
const Breadcrumb = () => {
  const { pathname } = useLocation();
  const pathnames = pathname.split("/").filter((x) => x);

  return (
    <div className="flex items-center justify-center p-10 ">
      <nav className="flex items-center space-x-2 text-sm text-gray-500 mt-4 ">
        <Link to="/" className="  text-black font-medium ">
          Home
        </Link>

        {pathnames.map((name, index) => {
          const routeTo = `/${pathnames.slice(0, index + 1).join("/")}`;
          const isLast = index === pathnames.length - 1;

          return (
            <div key={index} className="flex items-center space-x-2">
              <FaChevronRight className="w-4 h-4 text-black" />
              {isLast ? (
                <span className="capitalize text-gray-400">{name}</span>
              ) : (
                <Link
                  to={routeTo}
                  className="capitalize text-blue-600 font-medium "
                >
                  {name}
                </Link>
              )}
            </div>
          );
        })}
      </nav>
    </div>
  );
};

export default Breadcrumb;
