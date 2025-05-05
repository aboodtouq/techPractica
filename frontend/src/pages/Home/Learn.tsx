import { useState } from "react";
import Paginator from "../../components/ui/Paginator";
import SessionCard from "../../components/ui/SessionCard";
import { CookiesService, Modal, useAuthQuery } from "../../imports";
import { ISessionRes } from "../../interfaces";
import { useParams } from "react-router-dom";
import SessionCardDetails from "../../components/ui/SessionCardDetails";
import ApplySessionForm from "../../components/ApplySessionForm";

const Learn = () => {
  const { category } = useParams();
  const [isModalShowMoreOpen, setIsModalShowMoreOpen] = useState(false);
  const [selectedSession, setSelectedSession] = useState<ISessionRes>();
  const [selectedfields, setSelectedfieldss] = useState<string[]>();

  const [ApplyModel, setApplyModel] = useState(false);
  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 9;
  const token = CookiesService.get("UserToken");
  const Url = category
    ? `/sessions/category?categoryName=${category}&pageNumber=${
        page - 1
      }&pageSize=${sessionsPerPage}`
    : `/sessions/?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`;
  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: Url,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });
  //////////////
  const openModalShowMore = (session: ISessionRes) => {
    setSelectedSession(session);
    setSelectedfieldss(session.fields);

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
      category: "Cybersecurity",
      sessionDescription: "",
      sessionName: "",
      technologies: [""],
      fields: [""],
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
      category,
      sessionDescription,
      sessionName,
      technologies,
      id,
      fields,
      isPrivate,
    }: ISessionRes) => (
      <SessionCard
        openModal={() => {
          openModalShowMore({
            category,
            sessionDescription,
            sessionName,
            technologies,
            id,
            fields,
            isPrivate,
          });
        }}
        category={category}
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

      {/* مودال التقديم */}
      <Modal isOpen={ApplyModel} closeModal={CloseApplyModel} title={"Apply"}>
        <ApplySessionForm closeModal={CloseApplyModel} f={selectedfields} />
      </Modal>
    </>
  );
};

export default Learn;
