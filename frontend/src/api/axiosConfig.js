import axios from "axios";

const api = axios.create({

    baseURL: "http://localhost:8080/Better_Do_Math-1.0-SNAPSHOT/api"
});

api.interceptors.request.use(config => {

    const token =
        localStorage.getItem("token");

    if(token) {

        config.headers.Authorization =
            `Bearer ${token}`;
    }

    return config;
});

export default api;