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
    <div className="bg-white rounded-lg shadow-lg p-6 flex flex-col justify-between h-full max-w-sm border border-gray-200 relative">
      {/* Title and Category */}
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-lg font-bold text-gray-900">
          {slicer(session.sessionName, 20)}
        </h2>
        <span
          className={`${color} text-xs font-semibold px-3 py-1 rounded-full whitespace-nowrap`}
        >
          {session.category}
        </span>
      </div>

      {/* Description */}
      <p className="text-gray-600 text-sm mb-6 ">
        {slicer(session.sessionDescription, 100)}
      </p>

      {/* Technologies */}
      <div className="flex flex-wrap gap-2 mb-10">
        {session.technologies.map((tech) => (
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
