import axios from 'axios';

const baseUrl = import.meta.VITE_APP_DEV_API_URL + '/api/liput';

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