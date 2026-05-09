import { useContext }
from "react";

import { Link,
         useNavigate }
from "react-router-dom";

import { AuthContext }
from "../context/AuthContext";

function Navbar() {

    const { logout } =
        useContext(AuthContext);

    const navigate =
        useNavigate();

    const exit = () => {

        logout();

        navigate("/");
    };

    return (

        <nav>

            <Link to="/dashboard">
                Dashboard
            </Link>

            {" | "}

            <Link to="/exercise">
                Ejercicios
            </Link>

            {" | "}

            <button onClick={exit}>
                Logout
            </button>

        </nav>
    );
}

export default Navbar;