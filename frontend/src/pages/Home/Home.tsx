import { categories, features } from "../../data/data.ts";

const HomePage = () => {
  return (
    <>
      {/* HERO SECTION */}
      <div className="relative overflow-hidden bg-white h-[600px] flex items-center justify-center px-6">
        <img
          src="/src/assets/left-side.png"
          alt="Left Tech Logos"
          className="absolute left-0 top-0 h-full hidden lg:block"
        />
        <img
          src="/src/assets/right-side.png"
          alt="Right Tech Logos"
          className="absolute right-0 top-0 h-full hidden lg:block"
        />

        <div className="text-center max-w-2xl z-10">
          <div className="mb-4 inline-block rounded-full border border-gray-300 px-4 py-1 text-xs text-gray-500">
            Start Your Tech Journey Today
          </div>
          <h2 className="text-4xl font-bold text-center text-gray-800 mb-2">
            Lorem ipsum dolor sit amet.{" "}
          </h2>
          <div className="w-24 h-1 bg-gradient-to-r from-green-400 to-teal-500 mx-auto mt-4 rounded-full"></div>
          <p className="mt-6 text-gray-500">
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Assumenda
            alias doloremque, quasi sequi accusamus possimus molestiae ipsum hic
            ea. Veniam. <br />
            Get unlimited candidates and admins on every plan.
          </p>
          <div className="mt-8 flex justify-center">
            <input
              type="email"
              placeholder="Work email address"
              className="px-4 py-3 w-64 border border-gray-300 rounded-l-lg focus:outline-none"
            />
            <button className="bg-green-500 text-white px-6 py-3 rounded-r-lg hover:bg-green-600">
              Start Trial
            </button>
          </div>
        </div>
      </div>
      {/* CATEGORIES SECTION */}
      <section className="bg-gray-50 py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 className="text-2xl sm:text-3xl font-bold leading-tight text-gray-900 text-center mb-4">
            Explore Categories
          </h1>
          <div className="w-44 h-1 bg-gradient-to-r from-green-400 to-teal-500 mx-auto mb-10 rounded-full"></div>
          <div className="grid grid-cols-1  sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-8 text-left max-w-5xl mx-auto">
            {categories.map(({ Icon, title, style }, idx) => (
              <div
                key={idx}
                className="flex items-center flex-col space-x-4 space-y-4 justify-center"
              >
                <div className="flex-shrink-0">
                  <div className="bg-gray-100 p-4 rounded-full inline-flex items-center justify-center">
                    {<Icon className={style} />}
                  </div>
                </div>
                <div className="flex items-center">
                  <h3 className="text-base font-semibold text-gray-900">
                    {title}
                  </h3>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
      {/* FEATURES SECTION */}
      <section className="bg-white py-20">
        <h2 className="text-3xl font-bold text-center text-gray-800 mb-2">
          Why choose us?
        </h2>
        <div className="w-44 h-1 bg-gradient-to-r from-green-400 to-teal-500 mx-auto mb-12 rounded-full"></div>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 lg:grid-cols-4 gap-8 text-left max-w-5xl mx-auto">
            {features.map(({ Icon, title, description, style }, idx) => (
              <div
                key={idx}
                className="group bg-white  rounded-xl shadow-md p-6 text-center hover:shadow-lg transition duration-300 hover:-translate-y-1"
              >
                <div className="mb-4 flex justify-center">
                  <div className="bg-gray-100 p-4 rounded-full inline-flex items-center justify-center transition">
                    {<Icon className={style} />}
                  </div>
                </div>
                <h3 className="text-lg font-semibold text-gray-800 transition">
                  {title}
                </h3>
                <p className="text-sm text-gray-600 mt-2">{description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>
    </>
  );
};

export default HomePage;
