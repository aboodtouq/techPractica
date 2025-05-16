import { motion } from "framer-motion";
import { categories, features } from "../../data/data.ts";
import { useNavigate } from "react-router-dom";
import { CookiesService } from "../../imports.ts";

const HomePage = () => {
  document.title = "TechPractica | Home";

  const fadeIn = {
    hidden: { opacity: 0, y: 20 },
    visible: { opacity: 1, y: 0 },
  };
  const navigate = useNavigate();
  const token = CookiesService.get("UserToken");
  const txt = token ? "Start Learning" : "Get Started";
  return (
    <div className="overflow-hidden">
      {/* HERO SECTION - Enhanced with more vibrant gradient and better imagery */}
      <section className="relative bg-gradient-to-br from-[#f0fdf9] via-[#f8fafc] to-[#e0f2fe] h-[700px] flex items-center justify-center px-6">
        <div className="absolute inset-0 overflow-hidden">
          <img
            src="/src/assets/left-side.png"
            alt="Tech Logos"
            className="absolute left-0 top-0 h-full opacity-30 lg:opacity-70 object-cover hidden lg:block mix-blend-multiply"
          />
          <img
            src="/src/assets/right-side.png"
            alt="Tech Logos"
            className="absolute right-0 top-0 h-full opacity-30 lg:opacity-70 object-cover hidden lg:block mix-blend-multiply"
          />
          <div className="absolute inset-0 bg-gradient-to-r from-white/60 via-transparent to-white/60" />
        </div>

        <motion.div
          initial="hidden"
          animate="visible"
          variants={fadeIn}
          transition={{ duration: 0.8 }}
          className="text-center max-w-2xl z-10 px-4"
        >
          {!token && (
            <motion.div
              variants={fadeIn}
              transition={{ delay: 0.2 }}
              className="mb-6 inline-flex items-center rounded-full border border-gray-200 px-4 py-2 text-sm font-medium text-gray-700 bg-white/90 backdrop-blur-md shadow-sm hover:shadow-md transition-all"
            >
              Start Your Tech Journey Today
            </motion.div>
          )}
          <motion.h1
            variants={fadeIn}
            transition={{ delay: 0.3 }}
            className="text-5xl md:text-6xl font-bold text-gray-900 mb-6 leading-tight"
          >
            Turn Knowledge <span className="text-[#42D5AE]">into Action</span>
          </motion.h1>
          <motion.div
            variants={fadeIn}
            transition={{ delay: 0.4 }}
            className="w-32 h-1.5 bg-gradient-to-r from-[#42D5AE] to-[#022639] mx-auto rounded-full mb-8"
          />
          <motion.p
            variants={fadeIn}
            transition={{ delay: 0.5 }}
            className="mt-6 text-gray-700 text-xl leading-relaxed max-w-2xl mx-auto"
          >
            Build real-world projects and apply your technical skills in our
            immersive, hands-on learning environment.
          </motion.p>
          <motion.div
            variants={fadeIn}
            transition={{ delay: 0.6 }}
            className="mt-12 flex justify-center gap-4"
          >
            <button
              onClick={() => {
                token
                  ? navigate("/Learn", { replace: true })
                  : navigate("/User", { replace: true });
              }}
              className="bg-gradient-to-r from-[#42D5AE] to-[#38b28d]   hover:from-[#38b28d] hover:to-[#38b28d] text-white font-medium px-8 py-3.5 rounded-lg shadow-lg hover:shadow-xl transition-all duration-300 transform hover:-translate-y-0.5"
            >
              {txt}
            </button>
          </motion.div>
        </motion.div>
      </section>

      {/* CATEGORIES SECTION - More vibrant and interactive */}
      <section className="bg-[#f8fafc] py-20">
        <div className="max-w-7xl mx-auto px-6 sm:px-8 lg:px-12">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ duration: 0.6 }}
            className="text-center mb-16"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-4">
              Explore Categories
            </h2>

            <div className="w-44 h-1.5 bg-gradient-to-r from-[#42D5AE] to-[#022639] mx-auto mt-6 rounded-full" />
          </motion.div>

          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-6 max-w-6xl mx-auto">
            {categories.map(({ Icon, title, style }, idx) => (
              <motion.div
                key={idx}
                initial="hidden"
                whileInView="visible"
                viewport={{ once: true, margin: "-50px" }}
                variants={fadeIn}
                transition={{ duration: 0.5, delay: idx * 0.1 }}
                className="group flex flex-col items-center p-6 rounded-xl hover:bg-white hover:shadow-md transition-all duration-300 cursor-pointer"
                onClick={() => navigate(`/Learn/${title}`)}
              >
                <div className="bg-white p-4 rounded-full shadow-sm group-hover:shadow-md group-hover:bg-[#42D5AE]/10 transition-all mb-4">
                  <Icon className={`${style} text-xl`} />
                </div>
                <h3 className="text-base font-semibold text-gray-800 text-center">
                  {title}
                </h3>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* FEATURES SECTION - More elegant cards with gradient accents */}
      <section className="py-24 bg-gradient-to-b from-white to-[#f8fafc]">
        <div className="max-w-7xl mx-auto px-6 sm:px-8 lg:px-12">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ duration: 0.6 }}
            className="text-center mb-20"
          >
            <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-4">
              Why Choose Us?
            </h2>

            <div className="w-44 h-1.5 bg-gradient-to-r from-[#42D5AE] to-[#022639]  mx-auto mt-6 rounded-full" />
          </motion.div>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
            {features.map(({ Icon, title, description, style }, idx) => (
              <motion.div
                key={idx}
                initial="hidden"
                whileInView="visible"
                viewport={{ once: true, margin: "-50px" }}
                variants={fadeIn}
                transition={{ duration: 0.5, delay: idx * 0.1 }}
                className="bg-white rounded-xl p-8 shadow-sm hover:shadow-lg transition-all duration-300 hover:-translate-y-2 border border-gray-100"
              >
                <div className="bg-[#42D5AE]/10 p-3 rounded-full w-14 h-14 flex items-center justify-center mb-6 mx-auto">
                  <Icon className={`${style} text-xl`} />
                </div>
                <h3 className="text-lg font-semibold text-gray-800 text-center mb-3">
                  {title}
                </h3>
                <p className="text-gray-600 text-center text-sm leading-relaxed">
                  {description}
                </p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
