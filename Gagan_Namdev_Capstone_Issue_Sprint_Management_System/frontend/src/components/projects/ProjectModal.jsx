import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {
    createProject,
    updateProject,
} from "../../services/project-service";

import {
    getAllUsers,
} from "../../services/admin-service";

import "./ProjectModal.css";

function ProjectModal({

    closeModal,

    refreshProjects,

    editProject = null,

}) {

    const [users, setUsers] = useState([]);

    const [search, setSearch] = useState("");

    const [loading, setLoading] = useState(false);

    const [formData, setFormData] = useState({

        name: editProject?.name || "",

        description: editProject?.description || "",

        members: editProject?.members || [],

    });
    useEffect(() => {

        loadUsers();

    }, []);
    const loadUsers = async () => {

        try {

            const data = await getAllUsers();

            setUsers(data);

        }

        catch {

            toast.error("Failed to load users");

        }

    };
    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value,

        });

    };
    const handleMemberSelect = (email) => {

        if (formData.members.includes(email)) {

            setFormData({

                ...formData,

                members: formData.members.filter(

                    (member) => member !== email

                ),

            });

        }

        else {

            setFormData({

                ...formData,

                members: [

                    ...formData.members,

                    email,

                ],

            });

        }

    };
    const filteredUsers = users.filter((user) => {

        const keyword = search.toLowerCase();

        return (
            user.name?.toLowerCase().includes(keyword) ||
            user.email?.toLowerCase().includes(keyword)
        );

    });

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (!formData.name.trim()) {
            toast.error("Project Name is required");
            return;
        }

        if (!formData.description.trim()) {
            toast.error("Description is required");
            return;
        }

        try {

            setLoading(true);

            if (editProject) {

                await updateProject(
                    editProject._id,
                    formData
                );

                toast.success("Project Updated Successfully");

            } else {

                const response = await createProject(formData);

                toast.success(
                    `Project Created (${response.project_key})`
                );

            }

            refreshProjects();

            closeModal();

        } catch (error) {

            toast.error(
                error.response?.data?.message ||
                "Something went wrong"
            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="modal-overlay">

            <div className="project-modal">

                <h2>
                    {editProject
                        ? "Update Project"
                        : "Create Project"}
                </h2>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>Project Name</label>

                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            placeholder="Enter Project Name"
                        />

                    </div>

                    <div className="form-group">

                        <label>Description</label>

                        <textarea
                            rows="4"
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            placeholder="Enter Description"
                        />

                    </div>

                    <div className="form-group">

                        <label>Search Members</label>

                        <input
                            type="text"
                            placeholder="Search by name or email..."
                            value={search}
                            onChange={(e) =>
                                setSearch(e.target.value)
                            }
                        />

                    </div>
                    <div className="members-container">

                        {filteredUsers.length === 0 ? (

                            <p>No Users Found</p>

                        ) : (

                            filteredUsers.map((user) => (

                                <label
                                    key={user.email}
                                    className="member-item"
                                >

                                    <input
                                        type="checkbox"
                                        checked={formData.members.includes(
                                            user.email
                                        )}
                                        onChange={() =>
                                            handleMemberSelect(
                                                user.email
                                            )
                                        }
                                    />

                                    <div>

                                        <strong>
                                            {user.name}
                                        </strong>

                                        <br />

                                        <small>
                                            {user.email}
                                        </small>

                                    </div>

                                </label>

                            ))

                        )}

                    </div>

                    <p className="selected-count">

                        Selected Members :
                        {" "}
                        {formData.members.length}

                    </p>
                    <div className="modal-buttons">

                        <button
                            type="button"
                            className="cancel-btn"
                            onClick={closeModal}
                        >

                            Cancel

                        </button>

                        <button
                            type="submit"
                            className="save-btn"
                            disabled={loading}
                        >

                            {loading
                                ? "Please Wait..."
                                : editProject
                                    ? "Update Project"
                                    : "Create Project"}

                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default ProjectModal;