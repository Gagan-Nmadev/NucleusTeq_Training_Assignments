import api from "../config/axios";

export const getAllSprints = async () => {

    const response = await api.get(
        "/sprints"
    );

    return response.data;

};

export const getSprintById = async (id) => {

    const response = await api.get(
        `/sprints/${id}`
    );

    return response.data;

};

export const createSprint = async (data) => {

    const response = await api.post(
        "/sprints",
        data
    );

    return response.data;

};

export const updateSprint = async (id, data) => {

    const response = await api.put(
        `/sprints/${id}`,
        data
    );

    return response.data;

};

export const deleteSprint = async (id) => {

    const response = await api.delete(
        `/sprints/${id}`
    );

    return response.data;

};

export const startSprint = async (id) => {

    const response = await api.put(
        `/sprints/${id}/start`
    );

    return response.data;

};

export const completeSprint = async (id) => {

    const response = await api.put(
        `/sprints/${id}/complete`
    );

    return response.data;

};

export const addIssueToSprint = async (

    sprintId,

    issueId

) => {

    const response = await api.put(

        `/sprints/${sprintId}/add-issue`,

        {

            issue_id: issueId,

        }

    );

    return response.data;

};

export const removeIssueFromSprint = async (

    sprintId,

    issueId

) => {

    const response = await api.put(

        `/sprints/${sprintId}/remove-issue`,

        {

            issue_id: issueId,

        }

    );

    return response.data;

};

export const searchSprintByProject = async (

    projectId

) => {

    const response = await api.get(

        `/sprints/search/project/${projectId}`

    );

    return response.data;

};

export const searchSprintByStatus = async (

    status

) => {

    const response = await api.get(

        `/sprints/search/status/${status}`

    );

    return response.data;

};