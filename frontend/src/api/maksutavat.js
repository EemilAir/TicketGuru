import axios from "axios";

const baseUrl = "http://localhost:8080/api/maksutavat";

export const haeMaksutavat = async () => {
    try {
        const response = await axios.get(baseUrl, {withCredentials: true});
        return response.data;
    } catch (error) {
        console.error("Failed to fetch maksutavat:", error);
        return [];
    }
};