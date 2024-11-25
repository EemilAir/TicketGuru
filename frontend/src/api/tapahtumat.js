import axios from 'axios';

const baseUrl = "http://localhost:8080/api/tapahtumat/";

export const fetchTapahtumat = async () => {
    try {
        const response = await axios.get(baseUrl, {withCredentials: true});
        return response.data;
    } catch (error) {
        console.error("Fetching tapahtumat failed:", error);
    }
}