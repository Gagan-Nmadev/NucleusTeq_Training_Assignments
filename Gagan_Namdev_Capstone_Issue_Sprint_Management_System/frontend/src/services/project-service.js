import api from "../config/axios";

export const createProject = async (data) => {

    const response = await api.post(
        "/projects/",
        data
    );

    return response.data;

};

export const getProjects = async () => {

    const response = await api.get(
        "/projects/"
    );

    return response.data;

};

export const getMyProjects = async () => {

    const response = await api.get(
        "/projects/my"
    );

    return response.data;

};

export const getProjectById = async (id) => {

    const response = await api.get(
        `/projects/${id}`
    );

    return response.data;

};

export const updateProject = async (id, data) => {

    const response = await api.put(

        `/projects/${id}`,

        data

    );

    return response.data;

};

export const deleteProject = async (id) => {

    const response = await api.delete(

        `/projects/${id}`

    );

    return response.data;

};

export const assignMembers = async (

    id,

    members

) => {

    const response = await api.put(

        `/projects/${id}/members`,

        {

            members,

        }

    );

    return response.data;

};