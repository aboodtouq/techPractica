import { useState } from "react";
import Paginator from "../../components/ui/Paginator";
import SessionCard from "../../components/ui/SessionCard";
import { CookiesService, useAuthQuery } from "../../imports";
import { ISession } from "../../interfaces";

const Learn = () => {
  const [page, setPage] = useState<number>(1);
  const sessionsPerPage = 12;
  const token = CookiesService.get("UserToken");

  const { data: sessionData } = useAuthQuery({
    queryKey: [`SessionData-${page}`],
    url: `/sessions/?pageSize=${sessionsPerPage}&pageNumber=${page - 1}`,
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
  console.log(sessionData);
  const totalSessions = sessionData?.sessionsCount;
  const pageCount = Math.ceil(totalSessions / sessionsPerPage);

  return (
    <main className="min-h-screen container mx-auto p-10 pb-20 flex flex-col justify-between">
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 justify-items-center">
        {sessionData?.sessions.map((session: ISession, index: number) => (
          <SessionCard key={index} session={session} />
        ))}
      </div>

      {pageCount > 1 && (
        <div className="flex justify-end  ">
          <Paginator
            page={page}
            pageCount={pageCount}
            onClickNext={onClickNext}
            onClickPrev={onClickPrev}
          />
        </div>
      )}
    </main>
  );
};

export default Learn;
