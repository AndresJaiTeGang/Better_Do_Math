import { useEffect,
         useState }
from "react";

import Navbar
from "../components/Navbar";

import DashboardChart
from "../components/DashboardChart";

import * as dashboardService
from "../services/dashboardService";

function DashboardPage() {

    const [stats, setStats] =
        useState(null);

    useEffect(() => {

        const load = async () => {

            const data =
                await dashboardService
                    .getStats();

            setStats(data);
        };

        load();

    }, []);

    if(!stats)
        return <p>Cargando...</p>;

    return (

        <div>

            <Navbar />

            <h1>Dashboard</h1>

            <h3>
                Aciertos:
                {stats.correctos}
            </h3>

            <h3>
                Incorrectos:
                {stats.incorrectos}
            </h3>

            <h3>
                Porcentaje:
                {stats.porcentaje.toFixed(2)}%
            </h3>

            <DashboardChart
                correctos={stats.correctos}
                incorrectos={stats.incorrectos}
            />

        </div>
    );
}

export default DashboardPage;