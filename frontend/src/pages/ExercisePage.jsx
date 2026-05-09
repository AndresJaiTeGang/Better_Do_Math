import { useEffect, useState }
from "react";

import * as exerciseService
from "../services/exerciseService";

function ExercisePage() {

    const [exercise, setExercise] =
        useState(null);

    const [answer, setAnswer] =
        useState("");

    const [message, setMessage] =
        useState("");

    const loadExercise =
        async () => {

        const data =
            await exerciseService
                .getExercise();

        setExercise(data);

        setTimeout(() => {

            window.MathJax.typeset();

        }, 100);
    };

    useEffect(() => {

        loadExercise();

    }, []);

    const verify =
        async () => {

        const result =
            await exerciseService
                .sendAnswer({

                    numeroA:
                        exercise.numeroA,

                    numeroB:
                        exercise.numeroB,

                    respuesta:
                        parseInt(answer)
                });

        setMessage(
            result
                ? "✔ Correcto"
                : "❌ Incorrecto"
        );

        loadExercise();
    };

    if(!exercise)
        return <p>Cargando...</p>;

    return (

        <div>

            <h2>Ejercicio</h2>

            <div>

                {`\\[
                    ${exercise.numeroA}
                    +
                    ${exercise.numeroB}
                    = ?
                \\]`}

            </div>

            <input
                onChange={e =>
                    setAnswer(
                        e.target.value)}
            />

            <button onClick={verify}>
                Verificar
            </button>

            <h3>{message}</h3>

        </div>
    );
}

export default ExercisePage;