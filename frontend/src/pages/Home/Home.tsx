import { motion } from "framer-motion";
import { categories, features } from "../../data/data.ts";
import { useNavigate } from "react-router-dom";
import { CookiesService } from "../../imports.ts";

const HomePage = () => {
  const fadeIn = {
    hidden: { opacity: 0, y: 20 },
    visible: { opacity: 1, y: 0 },
  };
  const navigate = useNavigate();
  const token = CookiesService.get("UserToken");
  const txt = token ? "Start Learning" : "Get Started";
  return (
    <div className="overflow-hidden">
      {/* HERO SECTION */}
      <section className="relative bg-gradient-to-br from-[#f8fafc] to-white h-[600px] flex items-center justify-center px-6">
        <div className="absolute inset-0 overflow-hidden">
          <img
            src="/src/assets/left-side.png"
            alt="Tech Logos"
            className="absolute left-0 top-0 h-full opacity-20 lg:opacity-100 object-cover hidden lg:block"
          />
          <img
            src="/src/assets/right-side.png"
            alt="Tech Logos"
            className="absolute right-0 top-0 h-full opacity-20 lg:opacity-100 object-cover hidden lg:block"
          />
        </div>

        <motion.div
          initial="hidden"
          animate="visible"
          variants={fadeIn}
          transition={{ duration: 0.8 }}
          className="text-center max-w-2xl z-10"
        >
          {!token && (
            <motion.div
              variants={fadeIn}
              transition={{ delay: 0.2 }}
              className="mb-4 inline-block rounded-full border border-gray-200 px-4 py-1 text-xs text-gray-600 bg-white/80 backdrop-blur-sm"
            >
              Start Your Tech Journey Today
            </motion.div>
          )}
          <motion.h1
            variants={fadeIn}
            transition={{ delay: 0.3 }}
            className="text-4xl md:text-5xl font-bold text-gray-900 mb-4 leading-tight"
          >
            Turn Knowledge <span className="text-[#42D5AE]"> into Action</span>
          </motion.h1>
          <motion.div
            variants={fadeIn}
            transition={{ delay: 0.4 }}
            className="w-24 h-1 bg-[#42D5AE] mx-auto rounded-full"
          />
          <motion.p
            variants={fadeIn}
            transition={{ delay: 0.5 }}
            className="mt-6 text-gray-600 text-lg leading-relaxed"
          >
            Build real-world projects and apply your technical skills in a
            practical, hands-on environment.
          </motion.p>
          <motion.div
            variants={fadeIn}
            transition={{ delay: 0.6 }}
            className="mt-8"
          >
            <button
              onClick={() => {
                token
                  ? navigate("/Learn", { replace: true })
                  : navigate("/User/Register", { replace: true });
              }}
              className="bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium px-6 py-3 rounded-lg shadow-md hover:shadow-lg transition-all duration-300"
            >
              {txt}
            </button>
          </motion.div>
        </motion.div>
      </section>

      {/* CATEGORIES SECTION */}
      <section className="bg-[#f8fafc] py-20">
        <div className="max-w-7xl mx-auto px-6 sm:px-8 lg:px-12">
          <motion.div
            initial={{ opacity: 0 }}
            whileInView={{ opacity: 1 }}
            viewport={{ once: true }}
            transition={{ duration: 0.6 }}
            className="text-center"
          >
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-2">
              Explore Categories
            </h2>
            <div className="w-44 h-1 bg-[#42D5AE] mx-auto mb-12 rounded-full" />
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

      {/* FEATURES SECTION */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-6 sm:px-8 lg:px-12">
          <motion.div
            initial={{ opacity: 0 }}
            whileInView={{ opacity: 1 }}
            viewport={{ once: true }}
            transition={{ duration: 0.6 }}
            className="text-center mb-16"
          >
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900">
              Why Choose Us?
            </h2>
            <div className="w-44 h-1 bg-[#42D5AE] mx-auto mt-4 rounded-full" />
          </motion.div>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
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
