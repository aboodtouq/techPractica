import {
  Button,
  CookiesService,
  Modal,
  SessionCardUser,
  CreateSessionForm,
  useAuthQuery,
} from "../../imports.ts";
import useModal from "../../hooks/useModal.ts";
import { useState, useMemo, useEffect } from "react";
import Paginator from "../../components/ui/Paginator.tsx";
import { IErrorResponse, ISessionRes } from "../../interfaces.ts";
import EditSessionForm from "../../components/EditFormSession.tsx";
import SearchFilter from "../../components/ui/SearchFilter";
import { BiPlus } from "react-icons/bi";
import axiosInstance from "../../config/axios.config.ts";
import toast from "react-hot-toast";
import { AxiosError } from "axios";

const Projects = () => {
  document.title = "TechPractica | Sessions";

  const { isOpen, openModal, closeModal } = useModal();
  const [selectedSession, setSelectedSession] = useState<ISessionRes>();
  const [isModalEditOpen, setIsModalEditOpen] = useState(false);
  const [isOpenDeleteModal, setIsOpenDeleteModal] = useState(false);
  const [sessionId, setSessionId] = useState<number>();
  const [page, setPage] = useState(1);
  const [searchQuery, setSearchQuery] = useState("");
  const [activeFilter, setActiveFilter] = useState("all");
  const [filteredSessions, setFilteredSessions] = useState<ISessionRes[]>([]);

  const sessionsPerPage = 9;
  const token = CookiesService.get("UserToken");

  const { data: sessionData } = useAuthQuery({
    queryKey: ["SessionData-All"],
    url: `/sessions/users?pageSize=9999&pageNumber=0`,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });

  useEffect(() => {
    if (sessionData?.sessions) {
      let result = [...sessionData.sessions];

      if (activeFilter !== "all") {
        result = result.filter((s) => s.system === activeFilter);
      }

      if (searchQuery) {
        result = result.filter(
          (s) =>
            s.sessionName.toLowerCase().includes(searchQuery.toLowerCase()) ||
            s.system.toLowerCase().includes(searchQuery.toLowerCase()) ||
            s.technologies.some((tech: any) =>
              tech.toLowerCase().includes(searchQuery.toLowerCase())
            )
        );
      }

      const start = (page - 1) * sessionsPerPage;
      const end = start + sessionsPerPage;
      setFilteredSessions(result.slice(start, end));
      setPageCount(Math.ceil(result.length / sessionsPerPage));
    }
  }, [sessionData, searchQuery, activeFilter, page]);

  const [pageCount, setPageCount] = useState(1);

  const handleSearch = (query: string) => {
    setSearchQuery(query);
    setPage(1);
  };
  const onClickNext = () => {
    setPage((prev) => prev + 1);
  };
  const onClickPrev = () => {
    setPage((prev) => Math.max(prev - 1, 1));
  };

  const handleFilterChange = (filter: string) => {
    setActiveFilter(filter);
    setPage(1);
  };

  const openEditModal = (session: ISessionRes) => {
    setSelectedSession(session);
    setIsModalEditOpen(true);
  };

  const closeEditModal = () => {
    setSelectedSession(undefined);
    setIsModalEditOpen(false);
  };

  const openDeleteModal = (id: number) => {
    setSessionId(id);
    setIsOpenDeleteModal(true);
  };

  const closeDeleteModal = () => setIsOpenDeleteModal(false);

  const onSubmitRemoveSession = async () => {
    try {
      const response = await axiosInstance.delete(`/sessions/${sessionId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      toast.success(response.data, { position: "top-center" });
      setTimeout(() => {
        closeDeleteModal();
        window.location.reload();
      }, 500);
    } catch (error) {
      const err = error as AxiosError<IErrorResponse>;
      toast.error(`${err.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };

  const Data = useMemo(
    () =>
      filteredSessions.map((session) => (
        <SessionCardUser
          key={session.id}
          sessionId={session.id}
          system={session.system}
          sessionName={session.sessionName}
          sessionDescription={session.sessionDescription}
          technologies={session.technologies}
          openModal={() => openEditModal(session)}
          openDeleteModal={() => openDeleteModal(session.id)}
        />
      )),
    [filteredSessions]
  );

  const filterOptions = useMemo(
    () =>
      Array.from(
        new Set(
          sessionData?.sessions?.map((session: any) => session.system) || []
        )
      ) as string[],
    [sessionData]
  );

  return (
    <>
      <Modal isOpen={isOpen} title="ADD A NEW SESSION">
        <CreateSessionForm closeModal={closeModal} />
      </Modal>

      <Modal isOpen={isModalEditOpen} title="EDIT SESSION">
        <EditSessionForm
          session={selectedSession!}
          closeModal={closeEditModal}
        />
      </Modal>

      <Modal
        isOpen={isOpenDeleteModal}
        closeModal={closeDeleteModal}
        title="Are you sure you want to delete this session?"
        description="Deleting this session will remove it permanently from your account. This action cannot be undone. Make sure you no longer need this session or any related data before proceeding."
        Position="fixed top-10 left-1/2 transform -translate-x-1/2 z-50"
      >
        <div className="flex items-center space-x-3">
          <Button
            onClick={onSubmitRemoveSession}
            className="bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium transition-colors duration-200"
            width="w-full"
          >
            Yes, remove
          </Button>
          <Button
            onClick={closeDeleteModal}
            className="bg-white border border-gray-300 !text-[#022639] hover:bg-gray-50 font-medium transition-colors duration-200"
            width="w-full"
            type="button"
          >
            No
          </Button>
        </div>
      </Modal>

      <div className="container mx-auto pt-10 px-4 sm:px-6 lg:px-11">
        <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center gap-4 mb-6 flex-wrap">
          <div className="flex flex-col sm:flex-row gap-4 flex-1">
            <SearchFilter
              onSearch={handleSearch}
              onFilterChange={handleFilterChange}
              filterOptions={filterOptions}
              activeFilter={activeFilter}
              searchQuery={searchQuery}
            />
          </div>
          <Button
            className="w-full sm:w-fit bg-[#42D5AE] hover:bg-[#38b28d] text-white px-6 py-2 font-medium transition-colors duration-200 rounded-lg shadow-sm hover:shadow-md flex items-center justify-center"
            onClick={openModal}
          >
            <BiPlus size={18} className="mr-2" />
            Add Session
          </Button>
        </div>
      </div>

      <div className="min-h-screen flex flex-col -mt-5">
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
    </>
  );
};

export default Projects;
