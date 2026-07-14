import api from "../config/axios";

export const getAllUsers = async () => {

    const response =
        await api.get("/admin/users");

    return response.data;
};

export const deleteUser = async (userId) => {

    const response =
        await api.delete(`/admin/users/${userId}`);

    return response.data;
};

export const updateUserRole = async (
    userId,
    role
) => {

    const response = await api.put(
        `/admin/users/${userId}/role`,
        {
            role,
        }
    );

    return response.data;

};