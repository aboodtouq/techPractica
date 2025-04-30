import { CategoryColor, CategoryType } from "../../data/data";

interface SessionUserType {
  sessionName: string;
  sessionDescription: string;
  technologies: string[];
  category: CategoryType;
  openModal: () => void;
}

const SessionCardUser = ({
  category,
  openModal,
  sessionDescription,
  sessionName,
  technologies,
}: SessionUserType) => {
  const color = CategoryColor(category);

  return (
    <div className="bg-white rounded-lg shadow-lg p-4 sm:p-6 flex flex-col justify-between h-full w-full border border-gray-200 relative hover:shadow-md transition-shadow duration-200">
      {/* Title and Category */}
      <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center gap-2 mb-4">
        <h2 className="text-md font-bold text-gray-900 ">{sessionName}</h2>
        <span
          className={`${color}  text-xs font-semibold px-3 py-1 rounded-full whitespace-nowrap w-fit`}
        >
          {category}
        </span>
      </div>

      {/* Description */}
      <p className="text-gray-600 text-sm mb-4 sm:mb-6 ">
        {sessionDescription.trim().slice(0, 100)}
      </p>

      {/* Technologies */}
      <div className="flex flex-wrap gap-2 mb-12 sm:mb-10 max-h-7">
        {technologies.slice(0, 4).map((tech) => (
          <span
            key={tech}
            className="bg-gray-100 text-gray-700 text-xs font-medium px-2 py-1 rounded-md"
          >
            {tech}
          </span>
        ))}
        {technologies.length > 4 && (
          <span className="bg-gray-100 text-gray-700 text-xs font-medium px-2 py-1 rounded-md">
            +{technologies.length - 4}
          </span>
        )}
      </div>

      {/* Edit + Delete Buttons */}
      <div className="absolute bottom-4 left-4 right-4 flex gap-4 sm:gap-6 justify-end">
        <button
          onClick={openModal}
          className="text-sm font-medium text-gray-600 hover:text-blue-600 transition-colors px-2 py-1 rounded hover:bg-blue-50"
        >
          Edit
        </button>
        <button className="text-sm font-medium text-gray-600 hover:text-red-600 transition-colors px-2 py-1 rounded hover:bg-red-50">
          Delete
        </button>
      </div>
    </div>
  );
};

export default SessionCardUser;
