import { Routes, Route, Navigate } from "react-router-dom";

import LoginPage from "../pages/Login/LoginPage";
import RegisterPage from "../pages/Register/RegisterPage";

import ProtectedRoute from "./ProtectedRoute";
import MainLayout from "../layouts/MainLayout";

import DashboardPage from "../pages/dashboard/DashboardPage";
import ProjectsPage from "../pages/projects/ProjectsPage";
import IssuesPage from "../pages/issues/IssuesPage";
import SprintsPage from "../pages/sprints/SprintsPage";
import UsersPage from "../pages/users/UsersPage";
import Profile from "../pages/profile/Profile";

function NotFound() {
    return (
        <div style={{ padding: "40px" }}>
            <h2>404 - Page Not Found</h2>
        </div>
    );
}

export default function AppRoutes() {

    return (

        <Routes>

            <Route
                path="/"
                element={<Navigate to="/login" replace />}
            />


            <Route
                path="/login"
                element={<LoginPage />}
            />

            <Route
                path="/register"
                element={<RegisterPage />}
            />


            <Route
                element={
                    <ProtectedRoute>
                        <MainLayout />
                    </ProtectedRoute>
                }
            >


                <Route
                    path="/dashboard"
                    element={<DashboardPage />}
                />


                <Route
                    path="/projects"
                    element={<ProjectsPage />}
                />


                <Route
                    path="/issues"
                    element={<IssuesPage />}
                />


                <Route
                    path="/sprints"
                    element={<SprintsPage />}
                />

                {/* Profile */}
                <Route
                    path="/profile"
                    element={<Profile />}
                />

                {/* Admin Only */}
                <Route
                    path="/users"
                    element={
                        <ProtectedRoute allowedRoles={["admin"]}>
                            <UsersPage />
                        </ProtectedRoute>
                    }
                />

            </Route>

            <Route
                path="*"
                element={<NotFound />}
            />

        </Routes>

    );

}