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
import { IErrorResponse, ISessionRes } from "../../interfaces.ts";
import EditSessionForm from "../../components/EditFormSession.tsx";
import { BiPlus } from "react-icons/bi";
import axiosInstance from "../../config/axios.config.ts";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
const Projects = () => {
  const { isOpen, openModal, closeModal } = useModal();
  const [selectedSession, setSelectedSession] = useState<ISessionRes>();
  const [isModalEditOpen, setIsModalEditOpen] = useState(false);
  const [isOpenDeleteModal, setIsOpenDeleteModal] = useState(false);
  const [SessionId, setSessionId] = useState<number>();
  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 9;
  const token = CookiesService.get("UserToken");
  const closeDeleteModal = () => {
    setIsOpenDeleteModal(false);
  };
  const openDeleteModal = (sessionId: number) => {
    setSessionId(sessionId);
    setIsOpenDeleteModal(true);
  };
  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: `/sessions/users?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });
  const onClickNext = () => {
    setPage((prev) => prev + 1);
  };
  const openEditModal = (session: ISessionRes) => {
    setSelectedSession(session);
    setIsModalEditOpen(true);
  };
  const onSubmitRemoveTodo = async () => {
    try {
      const response = await axiosInstance.delete(`/sessions/${SessionId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success(response.data, { position: "top-center" });
      setTimeout(() => {
        closeDeleteModal();
        window.location.href = window.location.href;
      }, 500);
    } catch (error) {
      const ErrorObj = error as AxiosError<IErrorResponse>;

      toast.error(`${ErrorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };
  const closeEditModal = () => {
    setSelectedSession({
      system: "Cybersecurity",
      sessionDescription: "",
      sessionName: "",
      technologies: [""],
      categories: [""],
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
      system,
      sessionDescription,
      sessionName,
      technologies,
      id,
      categories,
      isPrivate,
    }: ISessionRes) => (
      <SessionCardUser
        openDeleteModal={() => {
          openDeleteModal(id);
        }}
        openModal={() => {
          openEditModal({
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
      >
        <div className="flex items-center space-x-3">
          <Button
            onClick={onSubmitRemoveTodo}
            className="bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium transition-colors duration-200"
            width="w-full"
          >
            Yes, remove
          </Button>
          <Button
            className="bg-white border border-gray-300 !text-[#022639] hover:bg-gray-50 font-medium transition-colors duration-200"
            width="w-full"
            type="button"
            onClick={closeDeleteModal}
          >
            Cancel
          </Button>
        </div>
      </Modal>
      <div className="container mx-auto pt-10 px-4 sm:px-6 lg:px-11">
        <div className="flex flex-col sm:flex-row items-start sm:items-center justify-end gap-4 mb-6">
          <Button
            className="w-full sm:w-fit bg-[#42D5AE] hover:bg-[#38b28d] text-white px-6 py-2 font-medium transition-colors duration-200 rounded-lg shadow-sm hover:shadow-md flex items-center justify-center"
            onClick={openModal}
          >
            <BiPlus size={18} className="mr-2" />
            Add Session
          </Button>
        </div>
      </div>
      <div className="lg:max-h-[900px] lg:min-h-[900px] min-h-screen flex flex-col -mt-5">
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
