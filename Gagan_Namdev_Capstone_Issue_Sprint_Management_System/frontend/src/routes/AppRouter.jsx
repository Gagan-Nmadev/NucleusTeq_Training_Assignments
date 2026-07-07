import { BrowserRouter, Routes, Route } from "react-router-dom";

import LoginPage from "../pages/login/LoginPage";
import RegisterPage from "../pages/register/RegisterPage";
import DashboardPage from "../pages/dashboard/DashboardPage";
import ProjectsPage from "../pages/projects/ProjectsPage";
import IssuesPage from "../pages/issues/IssuesPage";
import SprintsPage from "../pages/sprints/SprintsPage";
import CommentsPage from "../pages/comments/CommentsPage";

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/projects" element={<ProjectsPage />} />
        <Route path="/issues" element={<IssuesPage />} />
        <Route path="/sprints" element={<SprintsPage />} />
        <Route path="/comments" element={<CommentsPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;