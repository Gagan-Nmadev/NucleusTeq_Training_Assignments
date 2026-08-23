import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import "./ProjectsPage.css";
import ProjectModal from "../../components/projects/ProjectModal";

import {
    getProjects,
    deleteProject,
} from "../../services/project-service";

function ProjectsPage() {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const isAdmin = user?.role === "admin";

    const [projects, setProjects] = useState([]);
    const [filteredProjects, setFilteredProjects] = useState([]);
    const [search, setSearch] = useState("");
    const [showModal, setShowModal] = useState(false);
    const [editProject, setEditProject] = useState(null);

    useEffect(() => {
        loadProjects();
    }, []);

    useEffect(() => {

        const result = projects.filter((project) => {

            return (

                (project.name || "")
                    .toLowerCase()
                    .includes(search.toLowerCase())

                ||

                (project.project_key || "")
                    .toLowerCase()
                    .includes(search.toLowerCase())

            );

        });

        setFilteredProjects(result);

    }, [search, projects]);

    const loadProjects = async () => {

        try {

            const data = await getProjects();

            setProjects(data);
            setFilteredProjects(data);

        }

        catch {

            toast.error("Failed to load projects");

        }

    };

    const openCreateModal = () => {

        setEditProject(null);
        setShowModal(true);

    };

    const openEditModal = (project) => {

        setEditProject(project);
        setShowModal(true);

    };

    const closeModal = () => {

        setShowModal(false);
        setEditProject(null);

    };

    const handleDelete = async (projectId) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this project?"
        );

        if (!confirmDelete) return;

        try {

            await deleteProject(projectId);

            toast.success(
                "Project Deleted Successfully"
            );

            loadProjects();

        }

        catch (error) {

            toast.error(
                error.response?.data?.message ||
                "Failed to delete project"
            );

        }

    };

    return (

        <div className="projects-page">

            <div className="page-header">

                <div>

                    <h2>Projects</h2>

                    <p>

                        {
                            isAdmin
                                ? "Manage all projects"
                                : "Assigned Projects"
                        }

                    </p>

                </div>

                {
                    isAdmin && (

                        <button
                            className="create-btn"
                            onClick={openCreateModal}
                        >
                            + Create Project
                        </button>

                    )
                }

            </div>

            <div className="search-box">

                <input
                    type="text"
                    placeholder="Search Project..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                />

            </div>

            <div className="table-container">

                <table>

                    <thead>

                        <tr>

                            <th>Project Key</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Members</th>
                            <th>Created</th>

                            {
                                isAdmin &&
                                <th>Actions</th>
                            }

                        </tr>

                    </thead>

                    <tbody>

                        {

                            filteredProjects.length === 0

                                ?

                                (

                                    <tr>

                                        <td
                                            colSpan={
                                                isAdmin
                                                    ? 6
                                                    : 5
                                            }
                                            className="empty"
                                        >

                                            No Projects Found

                                        </td>

                                    </tr>

                                )

                                :

                                (

                                    filteredProjects.map((project) => (

                                        <tr key={project._id}>

                                            <td>

                                                {project.project_key}

                                            </td>

                                            <td>

                                                {project.name}

                                            </td>

                                            <td>

                                                {project.description}

                                            </td>

                                            <td>

                                                {project.members.length}

                                            </td>

                                            <td>

                                                {
                                                    new Date(
                                                        project.created_at
                                                    ).toLocaleDateString()
                                                }

                                            </td>

                                            {

                                                isAdmin && (

                                                    <td>

                                                        <button
                                                            className="edit-btn"
                                                            onClick={() =>
                                                                openEditModal(project)
                                                            }
                                                        >
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="delete-btn"
                                                            onClick={() =>
                                                                handleDelete(project._id)
                                                            }
                                                        >
                                                            Delete
                                                        </button>

                                                    </td>

                                                )

                                            }

                                        </tr>

                                    ))

                                )

                        }

                    </tbody>

                </table>

            </div>

            {

                showModal && (

                    <ProjectModal
                        closeModal={closeModal}
                        refreshProjects={loadProjects}
                        editProject={editProject}
                    />

                )

            }

        </div>

    );

}

export default ProjectsPage;