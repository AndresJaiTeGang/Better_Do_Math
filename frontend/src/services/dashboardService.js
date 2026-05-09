import api from "../api/axiosConfig";

export const getStats =
    async () => {

    const response =
        await api.get("/dashboard");

    return response.data;
};