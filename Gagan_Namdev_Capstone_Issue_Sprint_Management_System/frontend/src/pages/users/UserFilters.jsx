import "./UserFilters.css";

function UserFilters({

    search,

    setSearch,

    role,

    setRole,

}) {

    return (

        <div className="users-filters">

            <input

                type="text"

                placeholder="Search User..."

                value={search}

                onChange={(e) =>

                    setSearch(e.target.value)

                }

            />

            <select

                value={role}

                onChange={(e) => setRole(e.target.value)}

            >

                <option value="">All Roles</option>

                <option value="admin">Admin</option>

                <option value="member">Member</option>

                <option value="viewer">Viewer</option>

            </select>

        </div>

    );

}

export default UserFilters;