import { useState, useContext }
    from "react";

import { useNavigate }
    from "react-router-dom";

import { AuthContext }
    from "../context/AuthContext";

import * as authService
    from "../services/authService";

function LoginPage() {

    const [username, setUsername] =
        useState("");

    const [password, setPassword] =
        useState("");

    const { login } =
        useContext(AuthContext);

    const navigate =
        useNavigate();

    const handleLogin =
        async () => {

            try {

                const tokenData =
                    await authService.login(
                        username,
                        password
                    );

                login(tokenData);

                navigate("/dashboard");

            } catch {

                alert("Credenciales inválidas");
            }
        };

    return (

        <div>

            <h1>Better Do Math</h1>

            <input
                placeholder="Usuario"
                onChange={e =>
                    setUsername(e.target.value)}
            />

            <input
                type="password"
                placeholder="Contraseña"
                onChange={e =>
                    setPassword(e.target.value)}
            />

            <button
                onClick={handleLogin}>

                Iniciar Sesión

            </button>

        </div>
    );
}

export default LoginPage;