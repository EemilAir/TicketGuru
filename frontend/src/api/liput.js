const apiUrl = import.meta.env.VITE_APP_API_URL;

import { getToken } from '../utils/storage';

export async function fetchLiput() {
    const response = await fetch(apiUrl + '/api/liput', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + getToken()
        }
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Error ${response.status}: ${errorText}`);
    }

    return await response.json();
}

export async function fetchLippuById(id){
    const response = await fetch(apiUrl + '/api/liput/' + id, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + getToken()
        }
    });

    if(!response.ok){
        const errorText = await response.text();
        throw new Error(`Error ${response.status}: ${errorText}`);
    }

    return await response.json();
}

export async function editLippu(lippu){
    const id = lippu.lippuId;
    const used = lippu.kaytetty;

    const fetchConfig = {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        body: JSON.stringify({ kaytetty: !used }),
    }

    const response = await fetch(apiUrl + '/api/liput/' + id, fetchConfig);

    if(!response.ok){
        const error = await response.json();
        throw new Error('Error editing lippu', error);
    }

    return await response.json();
}