const Profile = () => {
  const skills = ["Node.js", "React", "Tailwind CSS", "MySQL"];

  return (
    <div className="bg-white  shadow-lg overflow-hidden transition-colors duration-200 !text-black h-200">
      <div className="relative h-48">
        <img
          src="https://res.cloudinary.com/djv4xa6wu/image/upload/v1735722161/AbhirajK/Abhirajk2.webp"
          alt="Cover"
          className="w-full h-full object-cover"
        />
        <div className="absolute -bottom-12 left-6">
          <img
            src="https://res.cloudinary.com/djv4xa6wu/image/upload/v1735722163/AbhirajK/Abhirajk%20mykare.webp"
            alt="Profile"
            className="w-24 h-24 rounded-xl object-cover border-4 border-white dark:border-gray-800 shadow-lg"
          />
        </div>
      </div>

      <div className="pt-16 px-6 pb-6">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
          <div>
            <h1 className="text-2xl font-bold  text-black ">Abhiraj K</h1>
            <p className="text-black ">Node.js Developer & Frontend Expert</p>
          </div>
        </div>

        <p className="mt-6 text-black ">
          Hi, I'm a passionate developer with expertise in Node.js, React, and
          Tailwind CSS. I love building efficient and scalable web applications.
        </p>

        <div className="mt-6">
          <h2 className="text-lg font-semibold text-black  mb-3">Skills</h2>
          <div className="flex flex-wrap gap-2">
            {skills.map((skill) => (
              <span
                key={skill}
                className="px-3 py-1 bg-purple-100 dark:bg-purple-900 text-black  dark:text-purple-300 rounded-lg text-sm font-medium"
              >
                {skill}
              </span>
            ))}
          </div>
        </div>

        <div className="mt-6">
          <h2 className="text-lg font-semibold text-black  dark:text-white mb-3">
            Contact
          </h2>
          <a
            href="mailto:abhirajk@example.com"
            className="inline-flex items-center text-black  hover:underline"
          >
            <svg
              className="w-5 h-5 mr-2"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
              />
            </svg>
            abhirajk@example.com
          </a>
        </div>
      </div>
    </div>
  );
};

export default Profile;
