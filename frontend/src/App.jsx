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

import ProtectedRoute
  from './components/ProtectedRoute'

function App() {

  return (

    <Routes>

      <Route
        path="/"
        element={<LoginPage />}
      />

      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <DashboardPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/exercise"
        element={
          <ProtectedRoute>
            <ExercisePage />
          </ProtectedRoute>
        }
      />

    </Routes>
  )
}

export default App