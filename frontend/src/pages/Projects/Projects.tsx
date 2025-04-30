import {
  Button,
  CookiesService,
  Modal,
  SessionCardUser,
  CreateSessionForm,
  useAuthQuery,
} from "../../imports.ts";
import useModal from "../../hooks/useModal.ts";
import { useState } from "react";
import Paginator from "../../components/ui/Paginator.tsx";
import { ISessionRes } from "../../interfaces.ts";
import EditSessionForm from "../../components/EditFormSession.tsx";
import { BiPlus } from "react-icons/bi";
const Projects = () => {
  const { isOpen, openModal, closeModal } = useModal();
  const [selectedSession, setSelectedSession] = useState<ISessionRes>();
  const [isModalEditOpen, setIsModalEditOpen] = useState(false);

  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 12;
  const token = CookiesService.get("UserToken");

  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: `/sessions/users?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });
  console.log(sessionData);
  const onClickNext = () => {
    setPage((prev) => prev + 1);
  };
  const openEditModal = (session: ISessionRes) => {
    setSelectedSession(session);
    setIsModalEditOpen(true);
  };
  const closeEditModal = () => {
    setSelectedSession({
      category: "Cybersecurity",
      sessionDescription: "",
      sessionName: "",
      technologies: [""],
      fields: [""],
      isPrivate: false,
      id: 4,
    });
    setIsModalEditOpen(false);
  };

  const onClickPrev = () => {
    setPage((prev) => Math.max(prev - 1, 1));
  };
  const totalSessions = sessionData?.sessionsCount || 0;
  const pageCount = Math.ceil(totalSessions / sessionsPerPage);
  const Data = sessionData?.sessions.map(
    ({
      category,
      sessionDescription,
      sessionName,
      technologies,
      id,
      fields,
      isPrivate,
    }: ISessionRes) => (
      <>
        <SessionCardUser
          category={category}
          openModal={() => {
            openEditModal({
              category,
              sessionDescription,
              sessionName,
              technologies,
              id,
              fields,
              isPrivate,
            });
          }}
          sessionDescription={sessionDescription}
          sessionName={sessionName}
          technologies={technologies}
          key={id}
        />
        {console.log(isPrivate)}{" "}
      </>
    )
  );
  console.log(sessionData?.sessions);
  return (
    <>
      <main className="min-h-screen container mx-auto p-10 pb-20 flex flex-col justify-between">
        <div className="flex md:flex-row flex-col items-center justify-between mb-5">
          <div className=" font-medium text-3xl ml-4">My Sessions</div>
          <Button
            className="bg-green-200 hover:bg-green-200 text-green-800 px-10 font-medium "
            width="w-fit"
            onClick={openModal}
          >
            <BiPlus size={18} className="mr-1" /> Add Session
          </Button>
        </div>
        <Modal
          isOpen={isOpen}
          closeModal={closeModal}
          title="ADD A NEW SESSION"
        >
          <CreateSessionForm closeModal={closeModal} />
        </Modal>
        <Modal
          isOpen={isModalEditOpen}
          closeModal={closeEditModal}
          title="EDIT SESSION"
        >
          <EditSessionForm
            session={selectedSession!}
            closeModal={closeEditModal}
          />
        </Modal>
        <div className="flex-1">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3  xl:grid-cols-4 gap-6 justify-items-center">
            {Data}
          </div>
        </div>
        {pageCount > 1 && (
          <div className="flex justify-end ">
            <Paginator
              page={page}
              pageCount={pageCount}
              onClickNext={onClickNext}
              onClickPrev={onClickPrev}
            />
          </div>
        )}
      </main>
    </>
  );
};

export default Projects;
