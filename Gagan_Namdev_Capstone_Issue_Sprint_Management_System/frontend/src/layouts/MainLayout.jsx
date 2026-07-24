import { Outlet } from "react-router-dom";

import Sidebar from "../components/sidebar/Sidebar";
import Topbar from "../components/topbar/Topbar";

import "./MainLayout.css";

function MainLayout() {
    return (
        <div className="layout">

            <Sidebar />

            <div className="main-section">

                <Topbar />

                <main className="page-content">
                    <Outlet />
                </main>

            </div>

        </div>
    );
}

export default MainLayout;