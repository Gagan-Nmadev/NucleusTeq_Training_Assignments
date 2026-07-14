import "./SprintFilters.css";

function SprintFilters({

    search,

    setSearch,

    status,

    setStatus,

    project,

    setProject,

    projects,

}) {

    return (

        <div className="filters-container">
             <input

                type="text"

                placeholder="Search Sprint..."

                value={search}

                onChange={(e) =>

                    setSearch(e.target.value)

                }

            />

            <select

                value={status}

                onChange={(e) =>

                    setStatus(e.target.value)

                }

            >

                <option value="">

                    All Status

                </option>

                <option value="PLANNED">

                    Planned

                </option>

                <option value="ACTIVE">

                    Active

                </option>

                <option value="COMPLETED">

                    Completed

                </option>

            </select>

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

        </div>

    );

}

export default SprintFilters;
        