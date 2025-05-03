import logo from "/src/assets/white.png";

const Footer = () => {
  return (
    <footer className="bg-[#022639] text-white w-full mt-auto border-t border-[#3b82f6]/30">
      <div className="max-w-7xl mx-auto px-6 py-8 flex flex-col sm:flex-row justify-between items-center">
        <div className="mb-4 sm:mb-0 transform transition duration-300 hover:scale-105">
          <img src={logo} className="h-20 w-auto" alt="TechPractica Logo" />
        </div>
        <div className="text-center sm:text-right">
          <p className="text-gray-200 text-sm">
            © {new Date().getFullYear()} TechPractica. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
