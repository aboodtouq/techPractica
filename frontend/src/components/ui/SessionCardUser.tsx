interface SessionUserType {
  sessionName: string;
  sessionDescription: string;
  technologies: string[];
  category: string;
}

const SessionCardUser = ({ session }: { session: SessionUserType }) => {
  return (
    <div className="bg-white rounded-lg shadow-lg p-6 flex flex-col justify-between h-full max-w-sm border border-gray-200 relative">
      {/* Title and Category */}
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-lg font-bold text-gray-900">
          {session.sessionName}
        </h2>
        <span className="bg-blue-100 text-blue-700 text-xs font-semibold px-3 py-1 rounded-full whitespace-nowrap">
          {session.category}
        </span>
      </div>

      {/* Description */}
      <p className="text-gray-600 text-sm mb-6">{session.sessionDescription}</p>

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
