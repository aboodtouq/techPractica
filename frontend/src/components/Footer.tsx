import logo from "/src/assets/white.png";

const Footer = () => {
  return (
    <footer className="bg-black text-white py-6 w-full mt-auto ">
      <div className="container mx-auto px-4 flex flex-col sm:flex-row justify-between items-center">
        {/* Logo */}
        <div className="text-xl font-bold text-white mb-4 sm:mb-0">
          <img src={logo} className="h-32 w-32" />
        </div>

        {/* Rights Text */}
        <div className="text-sm text-gray-400">
          © 2025 TechPractica All rights reserved.
        </div>
      </div>
    </footer>
  );
};

export default Footer;
