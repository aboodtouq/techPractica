import {
  Button,
  CookiesService,
  Modal,
  SessionCardUser,
  SessionForm,
  useAuthQuery,
} from "../../imports.ts";
import useModal from "../../hooks/useModal.ts";
import { useState } from "react";
import Paginator from "../../components/ui/Paginator.tsx";
import { ISession } from "../../interfaces.ts";
const Projects = () => {
  const { isOpen, openModal, closeModal } = useModal();
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

  const onClickPrev = () => {
    setPage((prev) => Math.max(prev - 1, 1));
  };
  const totalSessions = sessionData?.sessionsCount || 0;
  const pageCount = Math.ceil(totalSessions / sessionsPerPage);
  const Data = sessionData?.sessions.map((session: ISession, index: number) => (
    <SessionCardUser key={index} session={session} />
  ));
  return (
    <>
      <main className="min-h-screen container flex flex-col pb-30 justify-between">
        <div className="flex flex-row items-center justify-between m-10">
          <div className="  flex-1/2 font-medium text-3xl ml-4">
            My Sessions
          </div>
          <Button
            className=" bg-green-200 hover:bg-green-200 text-green-800 px-10 font-medium"
            onClick={openModal}
            width=" w-fit"
          >
            Add Session
          </Button>
        </div>
        <Modal
          isOpen={isOpen}
          closeModal={closeModal}
          title="ADD A NEW SESSION"
        >
          <SessionForm closeModal={closeModal} />
        </Modal>

        <div className="flex-1">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 justify-items-center">
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
