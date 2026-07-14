import { NavLink, useNavigate } from "react-router-dom";
import "./Sidebar.css";

function Sidebar() {

    const navigate = useNavigate();

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const role = user?.role?.toLowerCase();

    const logout = () => {

        localStorage.removeItem("token");
        localStorage.removeItem("user");

        navigate("/login");

    };

    return (

        <aside className="sidebar">

            <div>

                <h2 className="logo">
                    Issue & Sprint
                    <br />
                    Management System
                </h2>

                <nav>

                    <NavLink to="/dashboard">
                        Dashboard
                    </NavLink>

                    <NavLink to="/projects">
                        Projects
                    </NavLink>

                    <NavLink to="/issues">
                        Issues
                    </NavLink>

                    <NavLink to="/sprints">
                        Sprints
                    </NavLink>

                    {
                        role === "admin" && (

                            <NavLink to="/users">
                                Users
                            </NavLink>

                        )
                    }

                    <NavLink to="/profile">
                        Profile
                    </NavLink>

                </nav>

            </div>

            <div className="sidebar-bottom">

                <div className="role-text">
                    Role : {role?.toUpperCase()}
                </div>

                <button
                    className="logout-btn"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

        </aside>

    );

}

export default Sidebar;