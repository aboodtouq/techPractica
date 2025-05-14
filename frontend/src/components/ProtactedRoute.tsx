import { ReactNode } from "react";
import { Navigate } from "react-router-dom";

interface IProps {
  token: boolean;
  redirectPath: string;
  children: ReactNode;
}
const ProtactedRoute = ({ token, redirectPath, children }: IProps) => {
  if (token) return children;
  return <Navigate to={redirectPath} />;
};
export default ProtactedRoute;
