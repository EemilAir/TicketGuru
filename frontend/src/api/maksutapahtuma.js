import axios from 'axios';

const baseUrl = 'http://localhost:8080/api/maksutapahtumat/';

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