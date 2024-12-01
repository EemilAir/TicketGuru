import axios from 'axios';

const baseUrl = 'http://localhost:8080/api/liput';

export const updateLipunTila = async (koodi, tila) => {
    try {
        const response = await axios.patch(`${baseUrl}/${koodi}`, tila, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Failed to update ticket:", error);
        return null;
    }
}