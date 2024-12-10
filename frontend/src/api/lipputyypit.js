import axios from 'axios';

const baseUrl = import.meta.env.VITE_APP_DEV_API_URL + '/api/lipputyypit';

export const getLipputyypit = async () => {
    try {
        const response = await axios.get(baseUrl, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const createLipputyyppi = async (lipputyyppi) => {
    try {
        const response = await axios.post(baseUrl, lipputyyppi, { withCredentials: true, headers: { 'Content-Type': 'application/json' } });
        return response.data;
    } catch (error) {
        console.error(error);
    }
}

export const updateLipputyyppi = async (id, lipputyyppi) => {
    try {
        const response = await axios.patch(`${baseUrl}/${id}`, lipputyyppi, { withCredentials: true, headers: { 'Content-Type': 'application/json' } });
        return response.data;
    } catch (error) {
        console.error(error);
    }
}

export const deleteLipputyyppi = async (id) => {
    try {
        const response = await axios.delete(`${baseUrl}/${id}`, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error(error);
    }
}