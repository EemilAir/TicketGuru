import { BrowserRouter as Router, Route, Routes } from "react-router";
import { useAuth, AuthProvider } from "./components/AuthContext";
import LoginForm from "./components/LoginForm";
import Dashboard from "./components/Dashboard";

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    {/* Use PrivateRoute for protected route */}
                    <Route path="/" element={<PrivateRoute />} />
                    <Route path="/login" element={<LoginForm />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

// Private Route for authenticated users
const PrivateRoute = () => {
    const { isAuthenticated, loading } = useAuth();

    if (loading) {
        return <div>Loading...</div>; // Render a loading indicator while checking authentication
    }

    if (!isAuthenticated) {
        return <LoginForm />; // If not authenticated, show the Login form
    }

    return <Dashboard />; // Render Dashboard if the user is authenticated
};

export default App;