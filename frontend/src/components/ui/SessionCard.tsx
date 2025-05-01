import { CategoryType } from "../../data/data";

export interface SessionType {
  sessionName: string;
  sessionDescription: string;
  technologies: string[];
  category: CategoryType;
}

const SessionCard = ({ session }: { session: SessionType }) => {
  return (
    <div className="bg-white rounded-lg shadow-lg p-4 sm:p-6 flex flex-col h-full w-full border border-gray-200 hover:shadow-md transition-shadow duration-200 relative">
      {/* Header Section */}
      <div className="flex justify-between items-start gap-2 mb-3">
        <h2 className="text-md font-semibold text-[#022639] flex-1">
          {session.sessionName}
        </h2>
        <span className="bg-[#42D5AE]/10 text-[#022639] border border-[#42D5AE] text-xs font-semibold px-3 py-1 rounded-full whitespace-nowrap w-fit">
          {session.category}
        </span>
      </div>

      {/* Description */}
      <p className="text-gray-600 text-sm mb-4 sm:mb-6 line-clamp-3">
        {session.sessionDescription.trim().slice(0, 100)}
      </p>

      {/* Technologies Section */}
      <div className="flex flex-wrap gap-2 mb-12 sm:mb-10 max-h-7">
        {session.technologies.slice(0, 5).map((tech) => (
          <span
            key={tech}
            className="bg-gray-100 text-[#022639] text-xs font-medium px-2 py-1 rounded-md"
          >
            {tech}
          </span>
        ))}
        {session.technologies.length > 4 && (
          <span className="bg-gray-100 text-[#022639] text-xs font-medium px-2 py-1 rounded-md">
            +{session.technologies.length - 4}
          </span>
        )}
      </div>

      {/* Show More Button */}
      <button
        className={`absolute bottom-4 right-4 flex items-center gap-1 text-sm font-medium text-[#42D5AE] hover:[#42D5AE] transition-colors group`}
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
