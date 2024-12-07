import axios from 'axios';

const baseUrl = "http://localhost:8080/api/tapahtumat";

export const fetchTapahtumat = async () => {
    try {
        const response = await axios.get(baseUrl, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Fetching tapahtumat failed:", error);
        return error;
    }
}

export const editTapahtuma = async (id, editedFields) => {
    try {
        const url = `${baseUrl}/${id}`;
        const response = await axios.patch(url, editedFields, { withCredentials: true, headers: { 'Content-Type': 'application/json' } });
        return response.data;
    } catch (error) {
        console.error("Editing tapahtuma failed:", error);
        return error;
    }
};

export const fetchTapahtumaFormData = async () => {
    try {
        const url = `${baseUrl}/form-data`;
        const response = await axios.get(url, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Fetching tapahtuma failed:", error);
        return error;
    }
}

export const deleteTapahtuma = async (id) => {
    try {
        const url = `${baseUrl}/${id}`;
        const response = await axios.delete(url, { withCredentials: true });
        return response.data;
    } catch (error) {
        console.error("Deleting tapahtuma failed:", error);
        return error;
    }
}

export const luoTapahtuma = async (tapahtumaDTO) => {
    try {
        const response = await axios.post(baseUrl, JSON.stringify(tapahtumaDTO), { withCredentials: true, headers: { 'Content-Type': 'application/json' } });
        return response.data;
    } catch (error) {
        console.error(error);
    }
}