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

import RegisterPage
  from './pages/RegisterPage'

import AdminPage
  from './pages/AdminPage'

import AdminRoute
  from './components/AdminRoute'

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

      <Route
        path="/register"
        element={<RegisterPage />}
      />

      <Route
        path="/admin"
        element={
          <AdminRoute>
            <AdminPage />
          </AdminRoute>
        }
      />

    </Routes>
  )
}

export default App