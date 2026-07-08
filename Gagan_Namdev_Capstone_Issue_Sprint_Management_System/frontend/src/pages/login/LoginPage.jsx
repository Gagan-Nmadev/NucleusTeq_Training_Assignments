import { Navigate } from "react-router-dom";

function ProtectedRoute({ children }) {
  const isAuthenticated = localStorage.getItem("access_token");

  return isAuthenticated ? children : <Navigate to="/" replace />;
}

export default ProtectedRoute;