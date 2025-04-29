import { CategoryColor, CategoryType, slicer } from "../../data/data";

interface SessionUserType {
  sessionName: string;
  sessionDescription: string;
  technologies: string[];
  category: CategoryType;
}

const SessionCardUser = ({ session }: { session: SessionUserType }) => {
  const color = CategoryColor(session.category);

  return (
    <div className="bg-white rounded-lg shadow-lg p-4 sm:p-6 flex flex-col justify-between h-full w-full max-w-xs sm:max-w-sm md:max-w-md lg:max-w-lg xl:max-w-xl border border-gray-200 relative">
      {/* Title and Category */}
      <div className="flex  justify-between items-center mb-4">
        <h2 className="text font-bold text-gray-900">
          {slicer(session.sessionName, 20)}
        </h2>
        <span
          className={`${color} text-xs font-semibold px-3 py-1 rounded-full whitespace-nowrap`}
        >
          {session.category}
        </span>
      </div>
      {/* Description */}
      <p className="text-gray-600 text-sm mb-2 break-words overflow-hidden line-clamp-3 flex-grow">
        {slicer(session.sessionDescription, 100)}
      </p>
      {/* Technologies */}
      <div className="flex flex-wrap gap-2 mb-10">
        {session.technologies.slice(0, 5).map((tech) => (
          <span
            key={tech}
            className="bg-gray-100 text-gray-700 text-xs font-medium px-2 py-1 rounded-md"
          >
            {tech}
          </span>
        ))}
      </div>

      {/* Edit + Delete Buttons */}
      <div className="absolute bottom-4 left-4 right-4 flex gap-6 justify-end">
        <button className="text-sm font-medium text-gray-600 hover:text-blue-600 transition">
          Edit
        </button>
        <button className="text-sm font-medium text-gray-600 hover:text-red-600 transition">
          Delete
        </button>
      </div>
    </div>
  );
};

export default SessionCardUser;
