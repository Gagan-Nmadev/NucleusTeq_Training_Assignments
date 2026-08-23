import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {
    createIssue,
    updateIssue,
    getParentIssues,
} from "../../services/issue-service";

import { getProjects } from "../../services/project-service";

import { getAllUsers } from "../../services/admin-service";

import "./IssueModal.css";

const user = JSON.parse(localStorage.getItem("user"));
const isAdmin = user?.role === "admin";

function IssueModal({

    closeModal,

    refreshIssues,

    editIssue = null,

}) {

    const [projects, setProjects] = useState([]);

    const [users, setUsers] = useState([]);

    const [parentIssues, setParentIssues] = useState([]);

    const [loading, setLoading] = useState(false);

    const [formData, setFormData] = useState({

        title: editIssue?.title || "",

        description: editIssue?.description || "",

        project_id: editIssue?.project_id || "",

        assignee: editIssue?.assignee || "",

        priority: editIssue?.priority || "MEDIUM",

        issue_type: editIssue?.issue_type || "TASK",

        due_date: editIssue?.due_date || "",

        parent_issue_id:
            editIssue?.parent_issue_id || "",

    });

    useEffect(() => {

        loadProjects();

        loadUsers();

        if (editIssue?.project_id) {

            loadParentIssues(editIssue.project_id);

        }

    }, []);
    const loadProjects = async () => {

        try {

            const data = await getProjects();

            setProjects(data);

            if (!editIssue && data.length > 0) {

                setFormData((prev) => ({

                    ...prev,

                    project_id: prev.project_id || data[0]._id

                }));

                loadParentIssues(data[0]._id);

            }

        }

        catch {

            toast.error("Failed to load projects");

        }

    };

    const loadUsers = async () => {

        try {

            const data = await getAllUsers();

            setUsers(data);

        } catch {

            toast.error("Failed to load users");

        }

    };

    const loadParentIssues = async (projectId) => {

        if (!projectId) {

            setParentIssues([]);

            return;

        }

        try {

            const data = await getParentIssues(projectId);

            setParentIssues(data);

        } catch {

            setParentIssues([]);

        }

    };

    const handleChange = async (e) => {

        const { name, value } = e.target;

        setFormData((prev) => ({

            ...prev,

            [name]: value,

        }));

        if (name === "project_id") {

            await loadParentIssues(value);

        }

    };
    const handleSubmit = async (e) => {

        e.preventDefault();

        if (!formData.title.trim()) {

            toast.error("Issue Title is required");

            return;

        }

        if (!formData.description.trim()) {

            toast.error("Description is required");

            return;

        }

        if (!formData.project_id) {

            toast.error("Please select a project");

            return;

        }

        if (!formData.assignee) {

            toast.error("Please select an assignee");

            return;

        }

        try {

            setLoading(true);

            if (editIssue) {

                await updateIssue(
                    editIssue._id,
                    formData
                );

                toast.success(
                    "Issue Updated Successfully"
                );

            }

            else {

                await createIssue(formData);

                toast.success(
                    "Issue Created Successfully"
                );

            }

            refreshIssues();

            closeModal();

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Something went wrong"

            );

        }

        finally {

            setLoading(false);

        }

    };
    return (

        <div className="modal-overlay">

            <div className="issue-modal">

                <h2>

                    {
                        editIssue
                            ? "Update Issue"
                            : "Create Issue"
                    }

                </h2>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>Issue Title</label>

                        <input

                            type="text"

                            name="title"

                            value={formData.title}

                            onChange={handleChange}

                            placeholder="Enter Issue Title"

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

                        <label>Project</label>

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

                                        {project.project_key} - {project.name}

                                    </option>

                                ))

                            }

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Assignee</label>

                        <select
                            name="assignee"
                            value={formData.assignee}
                            onChange={handleChange}
                        >

                            <option value="">
                                Select Assignee
                            </option>

                            {

                                users
                                    .filter(
                                        (user) =>
                                            user.role === "member"
                                    )
                                    .map((user) => (

                                        <option
                                            key={user.email}
                                            value={user.email}
                                        >

                                            {user.name}

                                        </option>

                                    ))

                            }

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Issue Type</label>

                        <select

                            name="issue_type"

                            value={formData.issue_type}

                            onChange={handleChange}

                        >

                            <option value="EPIC">Epic</option>

                            <option value="STORY">Story</option>

                            <option value="TASK">Task</option>

                            <option value="BUG">Bug</option>

                            <option value="SUBTASK">Sub Task</option>

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Priority</label>

                        <select

                            name="priority"

                            value={formData.priority}

                            onChange={handleChange}

                        >

                            <option value="LOW">Low</option>

                            <option value="MEDIUM">Medium</option>

                            <option value="HIGH">High</option>

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Due Date</label>

                        <input
                            type="date"
                            name="due_date"
                            value={formData.due_date}
                            onChange={handleChange}
                            min={new Date().toISOString().split("T")[0]}
                        />
                    </div>

                    <div className="form-group">

                        <label>

                            Parent Issue (Optional)

                        </label>

                        <select

                            name="parent_issue_id"

                            value={formData.parent_issue_id}

                            onChange={handleChange}

                        >

                            <option value="">

                                None

                            </option>

                            {

                                parentIssues.map((issue) => (

                                    <option

                                        key={issue._id}

                                        value={issue._id}

                                    >

                                        {issue.title}

                                    </option>

                                ))

                            }

                        </select>

                    </div>
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

                            {

                                loading

                                    ? "Please Wait..."

                                    : editIssue

                                        ? "Update Issue"

                                        : "Create Issue"

                            }

                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default IssueModal;
