import { Button, Modal, SessionForm } from "../../imports.ts";
import useModal from "../../hooks/useModal.ts";
const Projects = () => {
  const { isOpen, openModal, closeModal } = useModal();

  return (
    <>
      <div className="flex flex-row items-center justify-between m-10">
        <div className="font-medium text-3xl ml-4">My Sessions</div>
        <div>
          {" "}
          <Button
            className=" bg-green-200 hover:bg-green-200 text-green-800  px-10  font-medium"
            onClick={openModal}
            width=" w-fit"
          >
            Add Session
          </Button>
        </div>
      </div>
      <Modal isOpen={isOpen} closeModal={closeModal} title="ADD A NEW SESSION">
        <SessionForm closeModal={closeModal} />
      </Modal>
      <div className="grid grid-cols-1 md:grid-cols-2  lg:grid-cols-3  xl:grid-cols-4   gap-3 ">
        ///Session
      </div>
    </>
  );
};

export default Projects;
