import { useEffect, useState } from "react";
import "./DashboardPage.css";

import { getProjects } from "../../services/project-service";
import { getAllIssues } from "../../services/issue-service";
import { getAllSprints } from "../../services/sprint-service";
import { getAllUsers } from "../../services/admin-service";

function DashboardPage() {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const role = user?.role;

    const [projects, setProjects] = useState([]);
    const [issues, setIssues] = useState([]);
    const [sprints, setSprints] = useState([]);
    const [users, setUsers] = useState([]);

    useEffect(() => {
        loadDashboard();
    }, []);

    const loadDashboard = async () => {

        try {

            const [
                projectData,
                issueData,
                sprintData
            ] = await Promise.all([
                getProjects(),
                getAllIssues(),
                getAllSprints()
            ]);

            setProjects(projectData);
            setIssues(issueData);
            setSprints(sprintData);

            if (role === "admin") {

                const userData = await getAllUsers();
                setUsers(userData);

            }

        }

        catch (error) {

            console.log(error);

        }

    };

    const completedIssues = issues.filter(
        issue => issue.status === "DONE"
    ).length;

    const pendingIssues = issues.filter(
        issue => issue.status !== "DONE"
    ).length;

    const activeSprints = sprints.filter(
        sprint => sprint.status === "ACTIVE"
    ).length;

    const completedSprints = sprints.filter(
        sprint => sprint.status === "COMPLETED"
    ).length;

    return (

        <div className="dashboard">

            <div className="dashboard-header">

                <div>

                    <h1>

                        Welcome, {user?.name}

                    </h1>

                    <p>

                        Logged in as <b>{role?.toUpperCase()}</b>

                    </p>

                </div>

            </div>

            <div className="stats-grid">

                <div className="stat-card">

                    <h4>

                        {
                            role === "admin"
                                ? "Total Projects"
                                : "Assigned Projects"
                        }

                    </h4>

                    <h2>{projects.length}</h2>

                </div>

                <div className="stat-card">

                    <h4>

                        {
                            role === "admin"
                                ? "Total Issues"
                                : "Assigned Issues"
                        }

                    </h4>

                    <h2>{issues.length}</h2>

                </div>

                <div className="stat-card">

                    <h4>

                        {
                            role === "admin"
                                ? "Total Sprints"
                                : "Assigned Sprints"
                        }

                    </h4>

                    <h2>{sprints.length}</h2>

                </div>

                {

                    role === "admin" && (

                        <div className="stat-card">

                            <h4>Total Users</h4>

                            <h2>{users.length}</h2>

                        </div>

                    )

                }

                <div className="stat-card">

                    <h4>Completed Issues</h4>

                    <h2>{completedIssues}</h2>

                </div>

                <div className="stat-card">

                    <h4>Pending Issues</h4>

                    <h2>{pendingIssues}</h2>

                </div>

                <div className="stat-card">

                    <h4>Active Sprints</h4>

                    <h2>{activeSprints}</h2>

                </div>

                <div className="stat-card">

                    <h4>Completed Sprints</h4>

                    <h2>{completedSprints}</h2>

                </div>

            </div>

        </div>

    );

}

export default DashboardPage;