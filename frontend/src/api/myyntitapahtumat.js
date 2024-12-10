import axios from 'axios';

const baseUrl = import.meta.env.VITE_APP_DEV_API_URL? import.meta.env.VITE_APP_DEV_API_URL + '/api/myyntitapahtumat' : '/api/myyntitapahtumat';

export const sellTickets = async (myyntitapahtuma) => {
    try {
        const response = await axios.post(baseUrl,
            myyntitapahtuma,
            {
                withCredentials: true,
                headers: { 'Content-Type': 'application/json' },
            }
        );
        return response.data;
    } catch (error) {
        console.error("Failed to sell tickets:", error);
        return null;
    }
};

export const fetchMyyntitapahtuma = async (id) => {
    try {
        const response = await axios.get(`${baseUrl}/${id}`, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Fetching myyntitapahtuma failed:", error);
        return null;
    }
}

export const fetchMyyntitapahtumat = async () => {
    try {
        const response = await axios.get(baseUrl, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Fetching myyntitapahtumat failed:", error);
        return [];
    }
};

export const deleteMyyntitapahtuma = async (id) => {
    try {
        await axios.delete(`${baseUrl}/${id}`, { withCredentials: true });
    } catch (error) {
        console.error("Failed to delete myyntitapahtuma:", error);
    }
}