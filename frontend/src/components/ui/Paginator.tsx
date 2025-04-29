interface PaginatorProps {
  page: number;
  pageCount: number;
  onClickPrev: () => void;
  onClickNext: () => void;
}

const Paginator = ({
  page,
  pageCount,
  onClickPrev,
  onClickNext,
}: PaginatorProps) => {
  return (
    <div className="flex justify-center items-center mt-8 gap-4">
      {/* Previous Button */}
      <button
        type="button"
        className="flex items-center justify-center px-4 h-10 rounded-md border bg-gray-800 text-white text-sm font-medium hover:bg-indigo-600 disabled:bg-gray-400 disabled:cursor-not-allowed"
        disabled={page <= 1}
        onClick={onClickPrev}
      >
        <svg
          className="w-4 h-4 mr-2 rtl:rotate-180"
          aria-hidden="true"
          fill="none"
          viewBox="0 0 14 10"
        >
          <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth="2"
            d="M13 5H1m0 0 4 4M1 5l4-4"
          />
        </svg>
        Previous
      </button>

      {/* Next Button */}
      <button
        type="button"
        className="flex items-center justify-center px-4 h-10 rounded-md border bg-gray-800 text-white text-sm font-medium hover:bg-indigo-600 disabled:bg-gray-400 disabled:cursor-not-allowed"
        disabled={page >= pageCount}
        onClick={onClickNext}
      >
        Next
        <svg
          className="w-4 h-4 ml-2 rtl:rotate-180"
          aria-hidden="true"
          fill="none"
          viewBox="0 0 14 10"
        >
          <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth="2"
            d="M1 5h12m0 0L9 1m4 4L9 9"
          />
        </svg>
      </button>
    </div>
  );
};

export default Paginator;
