import { useParams } from "react-router-dom";
import { CookiesService, useAuthQuery } from "../../imports";
import SessionCardReq from "../../components/ui/SessionCardReq";

interface IProps {}
const SessionRequests = ({}: IProps) => {
  const token = CookiesService.get("UserToken");

  const { id } = useParams();
  const { data } = useAuthQuery({
    queryKey: ["RrqData"],
    url: `/sessions/${id}/request`,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });
  const SessionReqRendar = data?.map(
    ({
      brief,
      categoryName,
      username,
    }: {
      username: string;
      categoryName: string;
      brief: string;
    }) => (
      <SessionCardReq
        brief={brief}
        categoryName={categoryName}
        username={username}
        key={username}
      />
    )
  );
  console.log(data);
  return (
    <>
      <div className="min-h-screen bg-gray-50 py-10 px-4 sm:px-6 lg:px-8">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-2xl font-bold text-[#022639] mb-5">
            Session Requests
          </h1>
          <div className="space-y-4">{SessionReqRendar}</div>
        </div>
      </div>
    </>
  );
};
export default SessionRequests;
