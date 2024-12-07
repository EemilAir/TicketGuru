import { createContext, useState, useContext, useEffect, useMemo } from "react";
import { checkAuth, login, logout } from '../api/auth';
const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username, setUsername] = useState(null);
    const [loading, setLoading] = useState(true); // Add loading state

    useEffect(() => {
        const checkAuthentication = async () => {
            try {
                const response = await checkAuth();
                setIsAuthenticated(true);
                setUsername(response.username);
            } catch (error) {
                setIsAuthenticated(false);
                setUsername(null);
                console.error(error);
            } finally {
                setLoading(false); // Set loading to false when done
            }
        };
        checkAuthentication();
    }, []);

    const handleLogin = async (username, password) => {
        try {
            const response = await login(username, password);
            setIsAuthenticated(true);
            setUsername(response.data.username);
        } catch (error) {
            setIsAuthenticated(false);
            setUsername(null);
            console.error(error);
        }
    }

    const handleLogout = async () => {
        try {
            await logout();
            setIsAuthenticated(false);
            setUsername(null);
        } catch (error) {
            console.error(error);
        }
    };

    const contextValue = useMemo(() => ({
        isAuthenticated,
        username,
        login: handleLogin,
        logout: handleLogout,
        loading
    }), [isAuthenticated, username, loading]);

    return (
        <AuthContext.Provider value={contextValue}>
            {children}
        </AuthContext.Provider>
    );
};
