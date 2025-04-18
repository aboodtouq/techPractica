import { slicer, tags } from "../data/data.ts";

const SessionCard = () => {
  const ReinderTags = tags.map((tag) => (
    <span
      key={tag.txt}
      className={`flex items-center ${tag.Mcolor} space-x-1  p-2 rounded-full text-xs font-semibold pointer`}
    >
      <span>{tag.txt}</span>
    </span>
  ));

  return (
    <div className="max-w-sm rounded-2xl overflow-hidden shadow-lg bg-white mx-auto ">
      <div className="p-4">
        <div className="flex items-center justify-between mb-2">
          <h2 className="text-xl font-semibold text-black">TechPractica</h2>
          <span className="bg-blue-800  text-xs font-semibold px-2 py-1 rounded-full text-white">
            Web Development
          </span>
        </div>
        <p className="text-sm text-gray-600 mb-4 ">
          {slicer(
            "In this project, you'll build a fully-functional task management app with a light/dark mode toggle. We provide a JSON file, so you can practice working with JSON data.",
            150
          )}
        </p>
        <div className="text-xs text-gray-400 mb-2">
          PERFECT FOR YOU, IF YOU ENJOY
        </div>

        <div className="flex flex-wrap gap-2 mb-4 text-white">
          {ReinderTags}
        </div>
        <button className="w-full hover:bg-blue-900 space-x-1  bg-blue-800 text-white font-semibold py-2 rounded-xl transition cursor-pointer">
          Show details
        </button>
      </div>
    </div>
  );
};

export default SessionCard;
