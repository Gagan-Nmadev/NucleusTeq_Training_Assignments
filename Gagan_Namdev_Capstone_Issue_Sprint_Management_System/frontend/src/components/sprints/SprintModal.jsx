import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {
    createSprint,
    updateSprint,
} from "../../services/sprint-service";

import {
    getProjects,
} from "../../services/project-service";

import "./SprintModal.css";

function SprintModal({

    closeModal,
    refreshSprints,
    editSprint = null,

}) {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const isAdmin = user?.role === "admin";

    const [projects, setProjects] = useState([]);

    const [formData, setFormData] = useState({

        name: editSprint?.name || "",

        description: editSprint?.description || "",

        goal: editSprint?.goal || "",

        project_id: editSprint?.project_id || "",

        start_date: editSprint?.start_date || "",

        end_date: editSprint?.end_date || "",

    });

    useEffect(() => {

        loadProjects();

    }, []);

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

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value,

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (!isAdmin) {

            toast.error(
                "Only Admin can manage sprints"
            );

            return;

        }

        if (

            !formData.name ||

            !formData.description ||

            !formData.goal ||

            !formData.project_id ||

            !formData.start_date ||

            !formData.end_date

        ) {

            toast.error(
                "Please fill all fields"
            );

            return;

        }

        try {

            if (editSprint) {

                await updateSprint(

                    editSprint._id,

                    formData

                );

                toast.success(
                    "Sprint Updated Successfully"
                );

            }

            else {

                await createSprint(
                    formData
                );

                toast.success(
                    "Sprint Created Successfully"
                );

            }

            refreshSprints();

            closeModal();

        }

        catch (error) {

            console.log(error);

            toast.error(

                editSprint

                    ?

                    "Sprint Update Failed"

                    :

                    "Sprint Creation Failed"

            );

        }

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>

                    {

                        editSprint

                            ?

                            "Update Sprint"

                            :

                            "Create Sprint"

                    }

                </h2>

                <form onSubmit={handleSubmit}>

                    <input

                        name="name"

                        placeholder="Sprint Name"

                        value={formData.name}

                        onChange={handleChange}

                    />

                    <textarea

                        name="description"

                        placeholder="Sprint Description"

                        value={formData.description}

                        onChange={handleChange}

                    />

                    <textarea

                        name="goal"

                        placeholder="Sprint Goal"

                        value={formData.goal}

                        onChange={handleChange}

                    />

                    <select

                        name="project_id"

                        value={formData.project_id}

                        onChange={handleChange}

                    >

                        <option value="">

                            Select Project

                        </option>

                        {

                            projects.map((project) => (

                                <option

                                    key={project._id}

                                    value={project._id}

                                >

                                    {project.name}

                                </option>

                            ))

                        }

                    </select>

                    <label>

                        Start Date

                    </label>

                    <input
                        type="date"
                        name="start_date"
                        value={formData.start_date}
                        onChange={handleChange}
                        min={new Date().toISOString().split("T")[0]}
                    />

                    <label>

                        End Date

                    </label>

                    <input
                        type="date"
                        name="end_date"
                        value={formData.end_date}
                        onChange={handleChange}
                        min={
                            formData.start_date ||
                            new Date().toISOString().split("T")[0]
                        }
                    />
                    <div

                        style={{

                            display: "flex",

                            gap: "10px",

                            marginTop: "20px"

                        }}

                    >

                        <button

                            className="primary-btn"

                            type="submit"

                            disabled={!isAdmin}

                        >

                            {

                                editSprint

                                    ?

                                    "Update"

                                    :

                                    "Create"

                            }

                        </button>

                        <button

                            type="button"

                            className="danger-btn"

                            onClick={closeModal}

                        >

                            Cancel

                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default SprintModal;