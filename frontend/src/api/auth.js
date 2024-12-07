import axios from 'axios';

const baseUrl = 'http://localhost:8080/api/auth';

export const checkAuth = async () => {
    try {
        const response = await axios.get(`${baseUrl}/check-auth`, { withCredentials: true });
        return response.data;
    } catch (error) {
        throw new Error("Failed to check authentication", error);
    }
}

export const login = async (username, password) => {
    try {
        const response = await axios.post(`${baseUrl}/login`, {
            username,
            password
        }, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true
        });
        return response;
    } catch (error) {
        throw new Error("Login failed:", error);
    }
};

export const logout = async () => {
    try {
        await axios.post(`${baseUrl}/logout`, {}, { withCredentials: true });
    } catch (error) {
        console.error("Logout failed:", error);
    }
}