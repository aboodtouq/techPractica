import { useState } from "react";
import Paginator from "../../components/ui/Paginator";
import SessionCard from "../../components/ui/SessionCard";
import { Modal, useAuthQuery } from "../../imports";
import { ISessionRes } from "../../interfaces";
import { useParams } from "react-router-dom";
import SessionCardDetails from "../../components/ui/SessionCardDetails";
import ApplySessionForm from "../../components/ApplySessionForm";

const Learn = () => {
  document.title = "TechPractica | Learn";

  const { category } = useParams();
  const [isModalShowMoreOpen, setIsModalShowMoreOpen] = useState(false);
  const [selectedSession, setSelectedSession] = useState<ISessionRes>();
  const [selectedfields, setSelectedfieldss] = useState<{
    SessionId: number;
    categories: string[];
  }>();

  const [ApplyModel, setApplyModel] = useState(false);
  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 9;
  const Url = category
    ? `/sessions/system?systemName=${category}&pageNumber=${
        page - 1
      }&pageSize=${sessionsPerPage}`
    : `/sessions/?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`;
  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: Url,
  });
  //////////////
  const openModalShowMore = (session: ISessionRes) => {
    setSelectedSession(session);
    setSelectedfieldss({
      categories: session.categories,
      SessionId: session.id,
    });

    setIsModalShowMoreOpen(true);
  };
  const openApplyModel = () => {
    setIsModalShowMoreOpen(false);
    setApplyModel(true);
  };
  const CloseApplyModel = () => {
    setApplyModel(false);
  };
  const closeModalShowMore = () => {
    setSelectedSession({
      system: "Cybersecurity",
      sessionDescription: "",
      sessionName: "",
      technologies: [""],
      categories: [""],
      isPrivate: false,
      id: 4,
    });
    setIsModalShowMoreOpen(false);
  };

  const onClickNext = () => {
    setPage((prev) => prev + 1);
  };

  const onClickPrev = () => {
    setPage((prev) => Math.max(prev - 1, 1));
  };

  const totalSessions = sessionData?.sessionsCount;
  const pageCount = Math.ceil(totalSessions / sessionsPerPage);
  const Data = sessionData?.sessions.map(
    ({
      system,
      sessionDescription,
      sessionName,
      technologies,
      id,
      categories,
      isPrivate,
    }: ISessionRes) => (
      <SessionCard
        openModal={() => {
          openModalShowMore({
            system,
            sessionDescription,
            sessionName,
            technologies,
            id,
            categories,
            isPrivate,
          });
        }}
        system={system}
        sessionDescription={sessionDescription}
        sessionName={sessionName}
        technologies={technologies}
        key={id}
      />
    )
  );
  return (
    <>
      <div className="lg:max-h-[900px] lg:min-h-[900px] min-h-screen flex flex-col">
        <main className="container mx-auto p-10 pb-20 flex-1 flex flex-col justify-between">
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-4">
            {Data}
          </div>

          {pageCount > 1 && (
            <div className="flex justify-start">
              <Paginator
                page={page}
                pageCount={pageCount}
                onClickNext={onClickNext}
                onClickPrev={onClickPrev}
              />
            </div>
          )}
        </main>
      </div>
      <Modal
        isOpen={isModalShowMoreOpen}
        closeModal={closeModalShowMore}
        title={selectedSession?.sessionName}
      >
        <SessionCardDetails
          session={selectedSession!}
          openModal={openApplyModel}
          closeModal={closeModalShowMore}
        />
      </Modal>

      <Modal isOpen={ApplyModel} closeModal={CloseApplyModel} title={"Apply"}>
        <ApplySessionForm
          closeModal={CloseApplyModel}
          SessionDet={selectedfields}
        />
      </Modal>
    </>
  );
};

export default Learn;
