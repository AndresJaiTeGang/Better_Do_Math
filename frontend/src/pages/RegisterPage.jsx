import { useState }
from "react";

import api
from "../api/axiosConfig";

import { useNavigate }
from "react-router-dom";

function RegisterPage() {

    const navigate =
        useNavigate();

    const [form, setForm] =
        useState({

            username: "",
            password: ""
        });

    const handle =
        e => {

        setForm({

            ...form,

            [e.target.name]:
                e.target.value
        });
    };

    const register =
        async () => {

        try {

            await api.post(
                "/auth/register",
                form
            );

            alert("Usuario creado");

            navigate("/");

        } catch {

            alert("Error");
        }
    };

    return (

        <div>

            <h1>Registro</h1>

            <input
                name="username"
                placeholder="Usuario"
                onChange={handle}
            />

            <input
                name="password"
                type="password"
                placeholder="Password"
                onChange={handle}
            />

            <button
                onClick={register}>

                Registrarse

            </button>

        </div>
    );
}

export default RegisterPage;