import axios from "axios";
import { API_BASE_URL } from "../config/api-config";

export const loginUser = async (loginData) => {
  const response = await axios.post(
    `${API_BASE_URL}/users/login`,
    loginData
  );

  return response.data;
};

export const registerUser = async (registerData) => {
  const response = await axios.post(
    `${API_BASE_URL}/users/register`,
    registerData
  );

  return response.data;
};