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

    if(response.ok){
        const data = await response.json();
        setToken(data.jwt);
        setUsername(username);
    } else {
        throw new Error("Kirjautuminen epäonnistui, tarkasta käyttäjänimi ja salasana");
    }
}

export { login };