import SessionCard from "../../components/ui/SessionCard";
import { CategoryType } from "../../data/data";
import { CookiesService, useAuthQuery } from "../../imports";

interface IProps {}
const Learn = ({}: IProps) => {
  const Token = CookiesService.get("UserToken");
  const { data: Session } = useAuthQuery({
    queryKey: ["SessionData"],
    url: "/sessions/?pageSize=12&pageNumber=0",
    config: {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    },
  });
  interface SessionType {
    sessionName: string;
    sessionDescription: string;
    technologies: string[];
    category: CategoryType;
  }
  const SessionData = Session?.map((session: SessionType, index: number) => (
    <SessionCard key={index} session={session} />
  ));
  return (
    <>
      <main className="container mx-auto">
        {" "}
        <div className="grid grid-cols-1 md:grid-cols-2  lg:grid-cols-3  xl:grid-cols-4   gap-2 ">
          {SessionData}
        </div>
      </main>
    </>
  );
};
export default Learn;
