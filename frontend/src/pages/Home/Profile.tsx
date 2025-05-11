import { FaGithub, FaLinkedin, FaCode, FaPaperPlane } from "react-icons/fa";
import { skills } from "../../data/data";
import { CookiesService, useAuthQuery } from "../../imports";
import { ISessionRes } from "../../interfaces";
import SessionCard from "../../components/ui/SessionCard";
import user from "../../assets/user.png";
const ProfilePage = () => {
  const token = CookiesService.get("UserToken");

  const socialLinks = [
    { icon: <FaGithub size={18} />, url: "#" },
    { icon: <FaLinkedin size={18} />, url: "#" },
  ];
  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-1`],
    url: `/sessions/users?pageSize=12&pageNumber=0`,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });
  const Data = sessionData?.sessions.map(
    ({
      system,
      sessionDescription,
      sessionName,
      technologies,
      id,
    }: ISessionRes) => (
      <SessionCard
        flag={false}
        openModal={() => {}}
        system={system}
        sessionDescription={sessionDescription}
        sessionName={sessionName}
        technologies={technologies}
        key={id}
      />
    )
  );
  return (
    <div className="min-h-screen flex flex-col bg-gray-50">
      <main className="flex-1">
        {/* Hero Section */}
        <div className="relative h-screen flex items-center justify-center bg-gradient-to-br from-[#42D5AE] to-[#38b28d]">
          <div className="absolute inset-0 bg-black/20"></div>

          <div className="relative z-10 text-center px-4 max-w-4xl mx-auto">
            <div className="mx-auto w-40 h-40 rounded-full border-4 border-white shadow-xl overflow-hidden mb-6">
              <img
                src={user}
                alt="Profile"
                className="w-full h-full object-cover"
              />
            </div>

            <h1 className="text-4xl md:text-5xl font-bold text-white mb-3">
              Abhiraj K
            </h1>
            <p className="text-xl text-white/90 mb-8">
              Full Stack Developer & JavaScript Expert
            </p>

            <div className="flex justify-center gap-4 mb-10">
              {socialLinks.map((link, index) => (
                <a
                  key={index}
                  href={link.url}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="w-12 h-12 flex items-center justify-center rounded-full bg-white/20 text-white hover:bg-white/30 backdrop-blur-sm transition-all"
                >
                  {link.icon}
                </a>
              ))}
            </div>

            <button className="px-8 py-3 bg-white text-[#022639] rounded-full font-medium hover:bg-gray-100 transition-colors shadow-lg flex items-center gap-2 mx-auto">
              <FaPaperPlane /> Contact Me
            </button>
          </div>
        </div>

        {/* About Section */}
        <section className="py-20 px-4 max-w-4xl mx-auto">
          <h2 className="text-3xl font-bold text-[#022639] mb-8 text-center">
            About Me
          </h2>

          <div className="grid md:grid-cols-2 gap-12">
            <div>
              <p className="text-gray-600 leading-relaxed mb-6">
                I'm a passionate developer with expertise in modern JavaScript
                frameworks and cloud technologies. I specialize in building
                scalable, performant web applications with clean, maintainable
                code.
              </p>
              <p className="text-gray-600 leading-relaxed">
                My approach combines technical excellence with user-focused
                design, creating solutions that are both powerful and intuitive.
                I thrive in collaborative environments and enjoy solving complex
                problems.
              </p>
            </div>

            <div>
              <h3 className="text-xl font-semibold text-[#022639] mb-4 flex items-center gap-2">
                <FaCode className="text-[#42D5AE]" /> Technical Skills
              </h3>
              <div className="flex flex-wrap gap-3">
                {skills.map((skill) => (
                  <span
                    key={skill}
                    className="px-4 py-2 bg-[#42D5AE]/10 text-[#022639] rounded-full font-medium"
                  >
                    {skill}
                  </span>
                ))}
              </div>
            </div>
          </div>
        </section>

        {/* Projects Showcase */}
        <section className="py-20 bg-gray-100">
          <div className="max-w-6xl mx-auto px-4">
            <h2 className="text-3xl font-bold text-[#022639] mb-12 text-center">
              My Projects
            </h2>

            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
              {Data}
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default ProfilePage;
