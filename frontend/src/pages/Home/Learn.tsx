import { useState } from "react";
import Paginator from "../../components/ui/Paginator";
import SessionCard from "../../components/ui/SessionCard";
import { CookiesService, useAuthQuery } from "../../imports";
import { ISession } from "../../interfaces";
import { useParams } from "react-router-dom";

const Learn = () => {
  const { category } = useParams();
  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 9;
  const token = CookiesService.get("UserToken");
  const Url = category
    ? `/sessions/category?categoryName=${category}&pageNumber=${
        page - 1
      }&pageSize=${sessionsPerPage}`
    : `/sessions/?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`;
  console.log(Url);
  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: Url,
    config: {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  });

  const onClickNext = () => {
    setPage((prev) => prev + 1);
  };

  const onClickPrev = () => {
    setPage((prev) => Math.max(prev - 1, 1));
  };

  const totalSessions = sessionData?.sessionsCount;
  const pageCount = Math.ceil(totalSessions / sessionsPerPage);

  return (
    <div className="lg:max-h-[900px] lg:min-h-[900px] min-h-screen flex flex-col">
      <main className="container mx-auto p-10 pb-20 flex-1 flex flex-col justify-between">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-4">
          {sessionData?.sessions.map((session: ISession, index: number) => (
            <SessionCard key={index} session={session} />
          ))}
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
  );
};

export default Learn;
