import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import "./SprintsPage.css";

import SprintTable from "../../components/sprints/SprintTable";
import SprintFilters from "../../components/sprints/SprintFilters";
import SprintModal from "../../components/sprints/SprintModal";

import {
    getAllSprints,
    deleteSprint,
    startSprint,
    completeSprint,
} from "../../services/sprint-service";

import { getProjects } from "../../services/project-service";

function SprintsPage() {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const isAdmin = user?.role === "admin";

    const [sprints, setSprints] = useState([]);
    const [filteredSprints, setFilteredSprints] = useState([]);
    const [projects, setProjects] = useState([]);

    const [search, setSearch] = useState("");
    const [status, setStatus] = useState("");
    const [project, setProject] = useState("");

    const [showModal, setShowModal] = useState(false);
    const [editSprint, setEditSprint] = useState(null);

    useEffect(() => {

        loadSprints();
        loadProjects();

    }, []);

    useEffect(() => {

        let result = [...sprints];

        if (search) {

            const keyword = search.toLowerCase();

            result = result.filter(

                (sprint) =>

                    sprint.name
                        ?.toLowerCase()
                        .includes(keyword)

                    ||

                    sprint.goal
                        ?.toLowerCase()
                        .includes(keyword)

            );

        }

        if (status) {

            result = result.filter(

                (sprint) =>
                    sprint.status === status

            );

        }

        if (project) {

            result = result.filter(

                (sprint) =>
                    sprint.project_id === project

            );

        }

        setFilteredSprints(result);

    }, [
        search,
        status,
        project,
        sprints,
    ]);

    const loadSprints = async () => {

        try {

            const data = await getAllSprints();

            setSprints(data);
            setFilteredSprints(data);

        }

        catch {

            toast.error(
                "Failed to load sprints"
            );

        }

    };

    const loadProjects = async () => {

        try {

            const data = await getProjects();

            setProjects(data);

        }

        catch {

            toast.error(
                "Failed to load projects"
            );

        }

    };

    const openCreateModal = () => {

        setEditSprint(null);
        setShowModal(true);

    };

    const openEditModal = (sprint) => {

        setEditSprint(sprint);
        setShowModal(true);

    };

    const closeModal = () => {

        setShowModal(false);
        setEditSprint(null);

    };

    const handleDelete = async (id) => {

        if (!window.confirm(
            "Delete this sprint?"
        )) return;

        try {

            await deleteSprint(id);

            toast.success(
                "Sprint deleted successfully"
            );

            loadSprints();

        }

        catch {

            toast.error(
                "Failed to delete sprint"
            );

        }

    };

    const handleStartSprint = async (id) => {

        try {

            await startSprint(id);

            toast.success(
                "Sprint started successfully"
            );

            loadSprints();

        }

        catch {

            toast.error(
                "Failed to start sprint"
            );

        }

    };

    const handleCompleteSprint = async (id) => {

        try {

            await completeSprint(id);

            toast.success(
                "Sprint completed successfully"
            );

            loadSprints();

        }

        catch {

            toast.error(
                "Failed to complete sprint"
            );

        }

    };

    return (

        <div className="sprints-page">

            <div className="page-header">

                <div>

                    <h2>Sprints</h2>

                    <p>

                        {

                            isAdmin

                                ? "Manage Project Sprints"

                                : "Assigned Project Sprints"

                        }

                    </p>

                </div>

                {

                    isAdmin && (

                        <button
                            className="create-btn"
                            onClick={openCreateModal}
                        >

                            + Create Sprint

                        </button>

                    )

                }

            </div>

            <SprintFilters

                search={search}
                setSearch={setSearch}

                status={status}
                setStatus={setStatus}

                project={project}
                setProject={setProject}

                projects={projects}

            />

            <SprintTable

                sprints={filteredSprints}

                projects={projects}

                openEditModal={openEditModal}

                handleDelete={handleDelete}

                handleStartSprint={handleStartSprint}

                handleCompleteSprint={handleCompleteSprint}

            />

            {

                showModal && (

                    <SprintModal

                        closeModal={closeModal}

                        refreshSprints={loadSprints}

                        editSprint={editSprint}

                    />

                )

            }

        </div>

    );

}

export default SprintsPage;