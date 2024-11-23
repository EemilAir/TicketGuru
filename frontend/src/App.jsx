import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router";
import { useAuth, AuthProvider } from "./components/AuthContext";
import LoginForm from "./components/LoginForm";
import Dashboard from "./components/Dashboard";

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    {/* Use PrivateRoute for protected route */}
                    <Route path="/" element={<PrivateRoute><Dashboard /></PrivateRoute>} />
                    <Route path="/login" element={<PublicRoute><LoginForm /></PublicRoute>} />
                </Routes>
            </Router>
        </AuthProvider>
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

export default App;