import api from "../api/axiosConfig";

export const getExercise =
    async () => {

    const response =
        await api.get("/ejercicio");

    return response.data;
};

export const sendAnswer =
    async (payload) => {

    const response =
        await api.post(
            "/resultado",
            payload
        );

    return response.data;
};