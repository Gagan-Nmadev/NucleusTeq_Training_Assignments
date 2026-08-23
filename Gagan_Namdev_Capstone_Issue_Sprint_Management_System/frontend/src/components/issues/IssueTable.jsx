import "./IssueTable.css";

function IssueTable({
    issues,
    projects,
    openEditModal,
    handleDelete,
    handleStatusUpdate,
}) {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    const isAdmin = user?.role === "admin";

    return (

        <div className="table-container">

            <table>

                <thead>

                    <tr>

                        <th>Title</th>
                        <th>Project</th>
                        <th>Assignee</th>
                        <th>Type</th>
                        <th>Priority</th>
                        <th>Status</th>
                        <th>Due Date</th>
                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        issues.length === 0 ?

                            (

                                <tr>

                                    <td
                                        colSpan="8"
                                        className="empty"
                                    >

                                        No Issues Found

                                    </td>

                                </tr>

                            )

                            :

                            (

                                issues.map((issue) => (

                                    <tr key={issue._id}>

                                        <td>

                                            {issue.title}

                                        </td>

                                        <td>

                                            {

                                                projects?.find(

                                                    (project) =>

                                                        project._id === issue.project_id

                                                )?.name ||

                                                issue.project_name ||

                                                "Unknown Project"

                                            }

                                        </td>

                                        <td>

                                            {issue.assignee}

                                        </td>

                                        <td>

                                            <span className="issue-type">

                                                {issue.issue_type}

                                            </span>

                                        </td>

                                        <td>

                                            <span
                                                className={`priority ${issue.priority?.toLowerCase()}`}
                                            >

                                                {issue.priority}

                                            </span>

                                        </td>

                                        <td>

                                            <span
                                                className={`status ${issue.status?.toLowerCase()}`}
                                            >

                                                {issue.status}

                                            </span>

                                        </td>

                                        <td>

                                            {

                                                issue.due_date

                                                    ?

                                                    new Date(
                                                        issue.due_date
                                                    ).toLocaleDateString()

                                                    :

                                                    "-"

                                            }

                                        </td>

                                        <td>

                                            {

                                                isAdmin ? (

                                                    <>

                                                        <button
                                                            className="edit-btn"
                                                            onClick={() =>
                                                                openEditModal(issue)
                                                            }
                                                        >
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="delete-btn"
                                                            onClick={() =>
                                                                handleDelete(issue._id)
                                                            }
                                                        >
                                                            Delete
                                                        </button>

                                                    </>

                                                ) : (

                                                    issue.assignee === user?.email &&

                                                    issue.status !== "DONE" && (

                                                        <button
                                                            className="edit-btn"
                                                            onClick={() =>
                                                                handleStatusUpdate(issue)
                                                            }
                                                        >
                                                            Update Status
                                                        </button>

                                                    )

                                                )

                                            }

                                        </td>

                                    </tr>

                                ))

                            )

                    }

                </tbody>

            </table>

        </div>

    );

}

export default IssueTable;