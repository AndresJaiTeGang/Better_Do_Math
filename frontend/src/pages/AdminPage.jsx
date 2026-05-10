import {
    useEffect,
    useState
}
    from "react";

import Navbar
    from "../components/Navbar";

import * as adminService
    from "../services/adminService";

function AdminPage() {

    const [users, setUsers] =
        useState([]);

    const load =
        async () => {

            const data =
                await adminService
                    .getUsers();

            setUsers(data);
        };

    useEffect(() => {

        load();

    }, []);

    const remove =
        async (id) => {

            await adminService
                .deleteUser(id);

            load();
        };

    const role =
        async (id, newRole) => {

            await adminService
                .changeRole(
                    id,
                    newRole
                );

            load();
        };

    return (

        <div>

            <Navbar />

            <h1>Admin Panel</h1>

            <table border="1">

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Rol</th>
                        <th>Acciones</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        users.map(u => (

                            <tr key={u.id}>

                                <td>{u.id}</td>

                                <td>
                                    {u.username}
                                </td>

                                <td>
                                    {u.rol}
                                </td>

                                <td>

                                    <button
                                        onClick={() =>
                                            role(
                                                u.id,
                                                u.rol === "ADMIN"
                                                    ? "ESTUDIANTE"
                                                    : "ADMIN"
                                            )
                                        }>

                                        Cambiar Rol

                                    </button>

                                    <button
                                        onClick={() =>
                                            remove(u.id)
                                        }>

                                        Eliminar

                                    </button>

                                </td>

                            </tr>
                        ))
                    }

                </tbody>

            </table>

        </div>
    );
}

export default AdminPage;