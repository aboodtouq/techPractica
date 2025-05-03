import { FaUser } from "react-icons/fa";
import { ISessionRes } from "../../interfaces.ts";
import { Button } from "../../imports.ts";
interface IProps {
  session: ISessionRes;
  closeModal: () => void;
}

const SessionCardDetails = ({ session, closeModal }: IProps) => {
  const cleaned = session.sessionDescription.replace(/^"(.*)"$/, "$1");

  return (
    <>
      {/* Role and Time */}
      <div className="flex justify-between items-center text-sm text-gray-600 mb-4">
        <div className="flex items-center gap-2">
          <FaUser />{" "}
          <div className="flex  flex-wrap gap-2 mb-12 sm:mb-0 max-h-7 ">
            {session.fields.map((field) => (
              <span
                key={field}
                className=" text-[#022639] text-xs font-medium  py-1 cursor-pointer"
              >
                {field}{" "}
              </span>
            ))}
          </div>
        </div>
      </div>

      {/* Description */}
      <div className="mb-4">
        <h3 className="font-semibold text-gray-800 mb-1">Description</h3>
        <div
          className="text-gray-600 text-sm mb-4 sm:mb-6"
          dangerouslySetInnerHTML={{ __html: cleaned }}
        />
      </div>

      {/* Tags */}
      <h4 className="font-semibold text-gray-800 mb-3">Tags</h4>
      <div className="flex  flex-wrap gap-2 mb-12 sm:mb-0 max-h-7 ">
        {session.technologies.map((tech) => (
          <span
            key={tech}
            className=" bg-gray-100 text-[#022639] text-xs font-medium px-2 py-1 rounded-md cursor-pointer"
          >
            {tech}
          </span>
        ))}
      </div>
      {/* Buttons */}
      <div className="flex mt-6 gap-4">
        <Button
          className="bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium transition-colors duration-200 cursor-pointer"
          width="w-full"
          type="submit"
        >
          Apply now
        </Button>
        <Button
          className="bg-white border border-gray-300 !text-[#022639] hover:bg-gray-50 font-medium transition-colors duration-200 cursor-pointer"
          width="w-full"
          type="button"
          onClick={() => {
            closeModal();
          }}
        >
          Cancel
        </Button>
      </div>
    </>
  );
};

export default SessionCardDetails;
