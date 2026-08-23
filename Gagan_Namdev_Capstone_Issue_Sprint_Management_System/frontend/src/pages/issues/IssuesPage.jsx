import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import "./IssuesPage.css";

import IssueTable from "../../components/issues/IssueTable";
import IssueFilters from "../../components/issues/IssueFilters";
import IssueModal from "../../components/issues/IssueModal";

import {
  getAllIssues,
  deleteIssue,
} from "../../services/issue-service";

import { getProjects } from "../../services/project-service";
import { getAllUsers } from "../../services/admin-service";

function IssuesPage() {

  const user = JSON.parse(localStorage.getItem("user"));
  const isAdmin = user?.role === "admin";

  const [issues, setIssues] = useState([]);
  const [filteredIssues, setFilteredIssues] = useState([]);

  const [search, setSearch] = useState("");

  const [showModal, setShowModal] = useState(false);
  const [editIssue, setEditIssue] = useState(null);

  const [projects, setProjects] = useState([]);
  const [users, setUsers] = useState([]);

  const [status, setStatus] = useState("");
  const [priority, setPriority] = useState("");
  const [project, setProject] = useState("");
  const [assignee, setAssignee] = useState("");

  useEffect(() => {

    loadIssues();
    loadProjects();
    loadUsers();

  }, []);

  useEffect(() => {
    const keyword = search.toLowerCase();

    const result = issues.filter((issue) => {

      const matchSearch =
        issue.title?.toLowerCase().includes(keyword) ||
        issue.assignee?.toLowerCase().includes(keyword) ||
        issue.priority?.toLowerCase().includes(keyword) ||
        issue.status?.toLowerCase().includes(keyword);

      const matchProject =
        !project || issue.project_id === project;

      const matchStatus =
        !status || issue.status === status;

      const matchPriority =
        !priority || issue.priority === priority;

      const matchAssignee =
        !assignee || issue.assignee === assignee;

      return (
        matchSearch &&
        matchProject &&
        matchStatus &&
        matchPriority &&
        matchAssignee
      );

    });

    setFilteredIssues(result);

  }, [
    search,
    issues,
    project,
    status,
    priority,
    assignee,
  ]);

  const loadIssues = async () => {

    try {

      const data = await getAllIssues();

      setIssues(data);
      setFilteredIssues(data);

    } catch {

      toast.error("Failed to load issues");

    }

  };
  const loadProjects = async () => {

    try {

      const data = await getProjects();

      setProjects(data);

    } catch {

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

  const openCreateModal = () => {

    setEditIssue(null);
    setShowModal(true);

  };

  const openEditModal = (issue) => {

    setEditIssue(issue);
    setShowModal(true);

  };

  const closeModal = () => {

    setShowModal(false);
    setEditIssue(null);

  };

  const handleDelete = async (id) => {

    if (!window.confirm("Delete this issue?")) {
      return;
    }

    try {
      await deleteIssue(id);

      toast.success("Issue deleted successfully");

      loadIssues();

    } catch {

      toast.error("Failed to delete issue");

    }

  };

  return (

    <div className="issues-page">

      <div className="page-header">

        <div>

          <h2>Issues</h2>

          <p>
            Manage all project issues
          </p>

        </div>

        {
          isAdmin && (

            <button
              className="create-btn"
              onClick={openCreateModal}
            >
              + Create Issue
            </button>

          )
        }

      </div>

      <IssueFilters

        search={search}

        setSearch={setSearch}

        status={status}

        setStatus={setStatus}

        priority={priority}

        setPriority={setPriority}
        project={project}

        setProject={setProject}

        assignee={assignee}

        setAssignee={setAssignee}

        projects={projects}

        users={users}

      />

      <IssueTable

        issues={filteredIssues}

        projects={projects}

        openEditModal={openEditModal}

        handleDelete={handleDelete}

        isAdmin={isAdmin}

      />

      {

        showModal && isAdmin && (

          <IssueModal

            closeModal={closeModal}

            refreshIssues={loadIssues}

            editIssue={editIssue}

          />

        )

      }

    </div>

  );

}

export default IssuesPage;