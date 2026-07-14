import { useEffect, useMemo, useState } from "react";
import toast from "react-hot-toast";

import "./UsersPage.css";

import UserFilters from "./UserFilters";
import UsersTable from "./UsersTable";

import {
    getAllUsers,
    deleteUser,
    updateUserRole,
} from "../../services/admin-service";

function UsersPage() {

    const [users, setUsers] = useState([]);

    const [loading, setLoading] = useState(true);

    const [search, setSearch] = useState("");

    const [role, setRole] = useState("");

    // Pagination
    const [currentPage, setCurrentPage] = useState(1);

    const usersPerPage = 8;

    useEffect(() => {

        fetchUsers();

    }, []);

    const fetchUsers = async () => {

        try {

            setLoading(true);

            const data = await getAllUsers();

            setUsers(data);

        } catch (error) {

            toast.error("Failed to load users");

        } finally {

            setLoading(false);

        }

    };

    // Delete User
    const handleDelete = async (userId) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this user?"
        );

        if (!confirmDelete) return;

        try {

            await deleteUser(userId);

            toast.success("User deleted successfully");

            fetchUsers();

        } catch (error) {

            toast.error(
                error?.response?.data?.message ||
                "Failed to delete user"
            );

        }

    };

    // Update User Role
    const handleRoleUpdate = async (userId, role) => {

        try {

            await updateUserRole(userId, role);

            toast.success("Role updated successfully");

            fetchUsers();

        } catch (error) {

            toast.error(
                error?.response?.data?.message ||
                "Failed to update role"
            );

        }

    };

    // Search & Filter
    const filteredUsers = useMemo(() => {

        return users.filter((user) => {

            const matchesSearch =

                user.name
                    ?.toLowerCase()
                    .includes(search.trim().toLowerCase()) ||

                user.email
                    ?.toLowerCase()
                    .includes(search.trim().toLowerCase());

            const matchesRole =

                role === "" ||

                user.role?.toLowerCase() === role.toLowerCase();

            return matchesSearch && matchesRole;

        });

    }, [users, search, role]);

    useEffect(() => {

        setCurrentPage(1);

    }, [search, role]);

    // Pagination
    const totalPages = Math.ceil(
        filteredUsers.length / usersPerPage
    );

    const indexOfLastUser =
        currentPage * usersPerPage;

    const indexOfFirstUser =
        indexOfLastUser - usersPerPage;

    const currentUsers =
        filteredUsers.slice(
            indexOfFirstUser,
            indexOfLastUser
        );

    const handlePrevious = () => {

        if (currentPage > 1) {

            setCurrentPage((prev) => prev - 1);

        }

    };

    const handleNext = () => {

        if (currentPage < totalPages) {

            setCurrentPage((prev) => prev + 1);

        }

    };

    return (

        <div className="users-page">

            <div className="page-header">

                <div>

                    <h2>Users</h2>

                    <p>View all registered users</p>

                </div>

                <div className="total-users">

                    Total Users : {filteredUsers.length}

                </div>

            </div>

            <UserFilters

                search={search}

                setSearch={setSearch}

                role={role}

                setRole={setRole}

            />

            {

                loading ?

                    (

                        <div className="loading">

                            Loading Users...

                        </div>

                    )

                    :

                    (

                        <>

                            <UsersTable

                                users={currentUsers}

                                handleDelete={handleDelete}

                                handleRoleUpdate={handleRoleUpdate}

                            />

                            {

                                totalPages > 1 && (

                                    <div className="pagination">

                                        <button

                                            onClick={handlePrevious}

                                            disabled={currentPage === 1}

                                        >

                                            Previous

                                        </button>

                                        <button className="active-page">

                                            {currentPage}

                                        </button>

                                        <button

                                            onClick={handleNext}

                                            disabled={currentPage === totalPages}

                                        >

                                            Next

                                        </button>

                                    </div>

                                )

                            }

                        </>

                    )

            }

        </div>

    );

}

export default UsersPage;