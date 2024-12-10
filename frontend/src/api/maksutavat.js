import axios from "axios";

const baseUrl = import.meta.env.VITE_APP_DEV_API_URL ? import.meta.env.VITE_APP_DEV_API_URL + "/api/maksutavat" : "/api/maksutavat";

export const haeMaksutavat = async () => {
    try {
        const response = await axios.get(baseUrl, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Failed to fetch maksutavat:", error);
        return [];
    }
};