import { useQuery } from "@tanstack/react-query";
import axiosInstance from "../config/axios.config";
import { AxiosRequestConfig } from "axios";
interface IQuery {
  queryKey: string[];
  url: string;
  config?: AxiosRequestConfig;
}
const useAuthQuery = ({ queryKey, url, config }: IQuery) => {
  return useQuery({
    queryKey,
    queryFn: async () => {
      const { data } = await axiosInstance.get(url, config);
      return data;
    },
  });
};
export default useAuthQuery;
