import { createContext, useState, useContext, useEffect } from "react";
import axios from "axios";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username, setUsername] = useState(null);
    const [loading, setLoading] = useState(true); // Add loading state

    useEffect(() => {
        axios.get('http://localhost:8080/api/auth/check-auth', { withCredentials: true })
            .then((response) => {
                if (response.status === 200 && response.data.username) {
                    setIsAuthenticated(true);
                    setUsername(response.data.username);
                } else {
                    setIsAuthenticated(false);
                    setUsername(null);
                }
            })
            .catch(() => {
                setIsAuthenticated(false);
                setUsername(null);
            })
            .finally(() => {
                setLoading(false); // Set loading to false after the check
            });
    }, []);

    const login = async (username, password) => {
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                username,
                password
            }, {
                headers: {
                    'Content-Type': 'application/json'
                },
                withCredentials: true
            });

            if (response.status === 200) {
                setIsAuthenticated(true);
                setUsername(response.data.username);
            }
        } catch (error) {
            console.error("Login failed:", error);
            setIsAuthenticated(false);
            setUsername(null);
        }
    };

    const logout = () => {
        setIsAuthenticated(false);
        setUsername(null);
        axios.post('http://localhost:8080/api/auth/logout', {}, { withCredentials: true });
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, username, login, logout, loading }}>
            {children}
        </AuthContext.Provider>
    );
};
