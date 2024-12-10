import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router";
import { useAuth } from "./components/AuthContext";
import LoginForm from "./components/LoginForm";
import Dashboard from "./components/Dashboard";
import Tapahtumat from "./components/Tapahtumat";
import Myyntitapahtuma from "./components/Myyntitapahtuma";
import PrivateLayout from "./components/PrivateLayout";
import UusiTapahtuma from "./components/UusiTapahtuma";
import Myyntitapahtumat from "./components/Myyntitapahtumat";
import Lipputyypit from "./components/Lipputyypit";
import './css/App.css';

const App = () => {
    return (
        <Router>
            <Routes>
                {/* Use PrivateRoute for protected route */}
                <Route path="/login" element={<PublicRoute><LoginForm /></PublicRoute>} />
                <Route path="/"
                    element={
                        <PrivateLayout header="Hallintapaneeli">
                            <PrivateRoute>
                                <Dashboard />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="/tapahtumat"
                    element={
                        <PrivateLayout header="Tapahtumat">
                            <PrivateRoute>
                                <Tapahtumat />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="/tapahtumat/uusi"
                    element={
                        <PrivateLayout header="Uusi tapahtuma">
                            <PrivateRoute>
                                <UusiTapahtuma />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="/myyntitapahtumat/:id"
                    element={
                        <PrivateLayout>
                            <PrivateRoute>
                                <Myyntitapahtuma />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="/myyntitapahtumat"
                    element={
                        <PrivateLayout header="Myyntitapahtumat">
                            <PrivateRoute>
                                <Myyntitapahtumat />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="/lipputyypit"
                    element={
                        <PrivateLayout header="Lipputyypit">
                            <PrivateRoute>
                                <Lipputyypit />
                            </PrivateRoute>
                        </PrivateLayout>
                    }
                />
                <Route path="*" element={<CatchAllRoute />} />
            </Routes>
        </Router>
    );
};

// Private Route for authenticated users
const PrivateRoute = ({ children }) => {
    const { isAuthenticated, loading } = useAuth();

    if (loading) {
        return <div>Loading...</div>; // Render a loading indicator while checking authentication
    }

    if (!isAuthenticated) {
        return <Navigate to="/login" />; // If not authenticated, show the Login form
    }

    return children; // Render Dashboard if the user is authenticated
};

// Private Route for authenticated users
const PublicRoute = ({ children }) => {
    const { isAuthenticated, loading } = useAuth();

    if (loading) {
        return <div>Loading...</div>; // Render a loading indicator while checking authentication
    }

    if (isAuthenticated) {
        return <Navigate to="/" />; // If authenticated, navigate to dashboard
    }

    return children; // Render Login page if not authenticated
};

// Catch all invalid routes and redirect to dashboard or login page based on the authentication status
const CatchAllRoute = () => {
    const { isAuthenticated, loading } = useAuth();

    if (loading) {
        return <div>Loading...</div>; // Render a loading indicator while checking authentication
    }

    if (isAuthenticated) {
        return <Navigate to="/" />;
    } else {
        return <Navigate to="/login" />;
    }
};

export default App;