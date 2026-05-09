import {
    Bar
} from "react-chartjs-2";

import {
    Chart as ChartJS,
    BarElement,
    CategoryScale,
    LinearScale
} from "chart.js";

ChartJS.register(
    BarElement,
    CategoryScale,
    LinearScale
);

function DashboardChart({
    correctos,
    incorrectos
}) {

    const data = {

        labels: [
            "Correctos",
            "Incorrectos"
        ],

        datasets: [

            {
                label: "Resultados",

                data: [
                    correctos,
                    incorrectos
                ]
            }
        ]
    };

    return <Bar data={data} />
}

export default DashboardChart;