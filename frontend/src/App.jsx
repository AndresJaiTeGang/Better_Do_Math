import {
    Routes,
    Route
} from 'react-router-dom'

import LoginPage
from './pages/LoginPage'

import DashboardPage
from './pages/DashboardPage'

import ExercisePage
from './pages/ExercisePage'

function App() {

    return (

        <Routes>

            <Route
                path="/"
                element={<LoginPage />}
            />

            <Route
                path="/dashboard"
                element={<DashboardPage />}
            />

            <Route
                path="/exercise"
                element={<ExercisePage />}
            />

        </Routes>
    )
}

export default App