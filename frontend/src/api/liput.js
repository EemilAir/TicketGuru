import axios from 'axios';

const baseUrl = 'http://localhost:8080/api/liput';

export const updateLipunTila = async (koodi, tila) => {
    try {
        console.log(koodi, tila);
        const response = await axios.patch(
            `${baseUrl}/${koodi}`,
            JSON.stringify({ tila }),
            {
                withCredentials: true,
                headers: { 'Content-Type': 'application/json' }
            });
        return response.data;
    } catch (error) {
        console.error("Failed to update ticket:", error);
        return null;
    }
}