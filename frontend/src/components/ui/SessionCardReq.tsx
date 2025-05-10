interface IProps {
  username: string;
  categoryName: string;
  brief: string;
}
const SessionCardReq = ({ brief, categoryName, username }: IProps) => {
  const cleaned = brief.replace(/^"(.*)"$/, "$1");

  return (
    <>
      <div className="border border-gray-200 rounded-lg p-4 shadow-sm hover:shadow-md transition-shadow">
        <div className="flex justify-between items-center mb-2">
          <h3 className="text-[#022639] font-semibold text-sm">{username}</h3>
          <span className="text-xs bg-[#42D5AE]/10 text-[#022639] border border-[#42D5AE] rounded-full px-2 py-0.5">
            {categoryName}
          </span>
        </div>
        <p
          className="text-sm text-gray-700 leading-relaxed mb-4"
          dangerouslySetInnerHTML={{ __html: cleaned }}
        />
        <div className="flex justify-end gap-2">
          <button className="px-3 py-1 text-sm text-white bg-[#42D5AE] rounded hover:bg-[#38b28d]">
            Accept
          </button>
          <button className="px-3 py-1 text-sm text-gray-600 border border-gray-300 rounded hover:bg-gray-100">
            Reject
          </button>
        </div>
      </div>
    </>
  );
};
export default SessionCardReq;
