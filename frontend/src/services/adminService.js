import api
from "../api/axiosConfig";

export const getUsers =
    async () => {

    const response =
        await api.get(
            "/admin/users"
        );

    return response.data;
};

export const deleteUser =
    async(id) => {

    await api.delete(
        `/admin/users/${id}`
    );
};

export const changeRole =
    async(id, role) => {

    await api.put(
        `/admin/users/${id}/role/${role}`
    );
};