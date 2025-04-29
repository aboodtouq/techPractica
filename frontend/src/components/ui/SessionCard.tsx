import { CategoryColor, CategoryType, slicer } from "../../data/data";
export interface SessionType {
  sessionName: string;
  sessionDescription: string;
  technologies: string[];
  category: CategoryType;
}
const SessionCard = ({ session }: { session: SessionType }) => {
  const color = CategoryColor(session.category);
  return (
    <div className="bg-white rounded-lg shadow-lg p-4 sm:p-6 flex flex-col justify-between h-full w-full max-w-xs sm:max-w-sm md:max-w-md lg:max-w-lg xl:max-w-xl border border-gray-200 relative">
      {/* Title and Category */}
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-md font-bold text-gray-900">
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

      {/* Show More Button */}
      <button
        className={`absolute bottom-4 right-4 flex items-center gap-1 text-sm font-medium  ${color} bg-white transition group`}
      >
        Show More
        <svg
          className="w-4 h-4 transform transition-transform duration-200 group-hover:translate-x-1"
          fill="none"
          stroke="currentColor"
          strokeWidth="2"
          viewBox="0 0 24 24"
        >
          <path strokeLinecap="round" strokeLinejoin="round" d="M9 5l7 7-7 7" />
        </svg>
      </button>
    </div>
  );
};

export default SessionCard;
