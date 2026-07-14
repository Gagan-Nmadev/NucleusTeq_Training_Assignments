import api from "../config/axios";

export const getAllIssues = async () => {

    const response = await api.get(
        "/issues"
    );

    return response.data;

};

export const getIssueById = async (id) => {

    const response = await api.get(
        `/issues/${id}`
    );

    return response.data;

};

export const createIssue = async (data) => {

    const response = await api.post(
        "/issues",
        data
    );

    return response.data;

};

export const updateIssue = async (id, data) => {

    const response = await api.put(
        `/issues/${id}`,
        data
    );

    return response.data;

};

export const deleteIssue = async (id) => {

    const response = await api.delete(
        `/issues/${id}`
    );

    return response.data;

};

export const updateIssueStatus = async (id, data) => {

    const response = await api.put(
        `/issues/${id}/status`,
        data
    );

    return response.data;

};

export const searchByStatus = async (status) => {

    const response = await api.get(
        `/issues/search/status/${status}`
    );

    return response.data;

};

export const searchByPriority = async (priority) => {

    const response = await api.get(
        `/issues/search/priority/${priority}`
    );

    return response.data;

};

export const searchByProject = async (projectId) => {

    const response = await api.get(
        `/issues/search/project/${projectId}`
    );

    return response.data;

};

export const searchByAssignee = async (email) => {

    const response = await api.get(
        `/issues/search/assignee/${email}`
    );

    return response.data;

};

export const getParentIssues = async (projectId) => {

    const response = await api.get(
        `/issues/project/${projectId}/parents`
    );

    return response.data;

};

export const getChildIssues = async (id) => {

    const response = await api.get(
        `/issues/${id}/children`
    );

    return response.data;

};