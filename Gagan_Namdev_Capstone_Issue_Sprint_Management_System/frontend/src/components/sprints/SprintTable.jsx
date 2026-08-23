import "./SprintTable.css";

function SprintTable({

    sprints,

    projects,

    openEditModal,

    handleDelete,

    handleStartSprint,

    handleCompleteSprint,

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

                        <th>Name</th>

                        <th>Goal</th>

                        <th>Project</th>

                        <th>Start Date</th>

                        <th>End Date</th>

                        <th>Status</th>

                        <th>Issues</th>

                        {

                            isAdmin &&

                            <th>Actions</th>

                        }

                    </tr>

                </thead>

                <tbody>

                    {

                        sprints.length === 0

                            ?

                            (

                                <tr>

                                    <td

                                        colSpan={
                                            isAdmin
                                                ? 8
                                                : 7
                                        }

                                        className="empty"

                                    >

                                        No Sprints Found

                                    </td>

                                </tr>

                            )

                            :

                            (

                                sprints.map((sprint) => (

                                    <tr key={sprint._id}>

                                        <td>

                                            {sprint.name}

                                        </td>

                                        <td>

                                            {sprint.goal || "-"}

                                        </td>

                                        <td>

                                            {

                                                projects?.find(

                                                    (project) =>

                                                        project._id === sprint.project_id

                                                )?.name ||

                                                sprint.project_name ||

                                                "Unknown Project"

                                            }

                                        </td>

                                        <td>

                                            {

                                                sprint.start_date

                                                    ?

                                                    new Date(

                                                        sprint.start_date

                                                    ).toLocaleDateString()

                                                    :

                                                    "-"

                                            }

                                        </td>

                                        <td>

                                            {

                                                sprint.end_date

                                                    ?

                                                    new Date(

                                                        sprint.end_date

                                                    ).toLocaleDateString()

                                                    :

                                                    "-"

                                            }

                                        </td>

                                        <td>

                                            <span

                                                className={`status ${sprint.status?.toLowerCase()}`}

                                            >

                                                {sprint.status}

                                            </span>

                                        </td>

                                        <td>

                                            {

                                                sprint.issues?.length || 0

                                            }

                                        </td>

                                        {

                                            isAdmin && (

                                                <td>

                                                    <button

                                                        className="edit-btn"

                                                        onClick={() =>

                                                            openEditModal(sprint)

                                                        }

                                                    >

                                                        Edit

                                                    </button>

                                                    <button

                                                        className="delete-btn"

                                                        onClick={() =>

                                                            handleDelete(sprint._id)

                                                        }

                                                    >

                                                        Delete

                                                    </button>

                                                    <button

                                                        className="start-btn"

                                                        onClick={() =>

                                                            handleStartSprint(sprint._id)

                                                        }

                                                    >

                                                        Start

                                                    </button>

                                                    <button

                                                        className="complete-btn"

                                                        onClick={() =>

                                                            handleCompleteSprint(sprint._id)

                                                        }

                                                    >

                                                        Complete

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

    );

}

export default SprintTable;