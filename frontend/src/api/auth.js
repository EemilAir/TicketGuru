import { setToken, setUsername } from '../utils/storage.js';

const api_url = import.meta.env.VITE_APP_API_URL;

async function login(username, password){
    const response = await fetch(`${api_url}/api/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
            "kayttajatunnus": username, 
            "salasana": password 
        })
    });

    const json = await response.json();

    if(response.ok){
        setToken(json.jwt);
        setUsername(username);
    } else {
        throw new Error(json.message);
    }
}

export { login };