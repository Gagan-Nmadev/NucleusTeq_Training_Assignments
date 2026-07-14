import "./IssueFilters.css";

function IssueFilters({

    search,

    setSearch,

    status,

    setStatus,

    priority,

    setPriority,

    project,

    setProject,

    assignee,

    setAssignee,

    projects,

    users,

}) {

    return (

        <div className="issue-filters">

            <input

                type="text"

                placeholder="Search Issue..."

                value={search}

                onChange={(e) =>
                    setSearch(e.target.value)
                }

            />

            <select

                value={project}

                onChange={(e) =>
                    setProject(e.target.value)
                }

            >

                <option value="">
                    All Projects
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

            <select

                value={status}

                onChange={(e) =>
                    setStatus(e.target.value)
                }

            >

                <option value="">
                    All Status
                </option>

                <option value="TODO">
                    TODO
                </option>

                <option value="IN_PROGRESS">
                    IN PROGRESS
                </option>

                <option value="DONE">
                    DONE
                </option>

            </select>
            <select

                value={priority}

                onChange={(e) =>
                    setPriority(e.target.value)
                }

            >

                <option value="">
                    All Priority
                </option>

                <option value="LOW">
                    Low
                </option>

                <option value="MEDIUM">
                    Medium
                </option>

                <option value="HIGH">
                    High
                </option>

            </select>

            <select

                value={assignee}

                onChange={(e) =>
                    setAssignee(e.target.value)
                }

            >

                <option value="">
                    All Assignees
                </option>

                {

                    users.map((user) => (

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

    );

}

export default IssueFilters;