import { createContext, useState } from "react";

export const AuthContext =
    createContext();

export const AuthProvider = ({ children }) => {

    const [token, setToken] =
        useState(
            localStorage.getItem("token")
        );

    const login = (data) => {

        localStorage.setItem(
            "token",
            data.token
        );

        localStorage.setItem(
            "role",
            data.rol
        );

        setToken(data.token);
        setRole(data.rol);
    };

    const logout = () => {

        localStorage.removeItem("role");

        setRole(null);
    };

    const [role, setRole] =
        useState(
            localStorage.getItem("role")
        );

    return (

        <AuthContext.Provider
            value={{
                token,
                role,
                login,
                logout
            }}>

            {children}

        </AuthContext.Provider>
    );
};