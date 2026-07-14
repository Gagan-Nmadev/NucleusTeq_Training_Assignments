import "./UsersTable.css";

function UsersTable({
    users,
    handleDelete,
    handleRoleUpdate,
}) {

    return (

        <div className="table-container">

            <table>

                <thead>

                    <tr>

                        <th>Avatar</th>

                        <th>Full Name</th>

                        <th>Email</th>

                        <th>Role</th>

                        <th>Status</th>

                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        users.length === 0 ?

                            (

                                <tr>

                                    <td
                                        colSpan="6"
                                        className="empty"
                                    >

                                        No Users Found

                                    </td>

                                </tr>

                            )

                            :

                            (

                                users.map((user) => (

                                    <tr key={user._id}>

                                        <td>

                                            <div className="avatar">

                                                {
                                                    user.name
                                                        ? user.name.charAt(0).toUpperCase()
                                                        : "U"
                                                }

                                            </div>

                                        </td>

                                        <td>{user.name}</td>

                                        <td>{user.email}</td>

                                        <td>

                                            <span
                                                className={`role ${user.role?.toLowerCase()}`}
                                            >

                                                {user.role}

                                            </span>

                                        </td>

                                        <td>

                                            <span className="status active">

                                                Active

                                            </span>

                                        </td>

                                        <td className="action-buttons">

                                            <select
                                                defaultValue={user.role}
                                                onChange={(e) =>
                                                    handleRoleUpdate(
                                                        user._id,
                                                        e.target.value
                                                    )
                                                }
                                            >

                                                <option value="admin">
                                                    Admin
                                                </option>

                                                <option value="member">
                                                    Member
                                                </option>

                                                <option value="viewer">
                                                    Viewer
                                                </option>

                                            </select>

                                            <button
                                                className="delete-btn"
                                                onClick={() =>
                                                    handleDelete(user._id)
                                                }
                                            >

                                                Delete

                                            </button>

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

export default UsersTable;