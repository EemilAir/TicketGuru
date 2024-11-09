import { useState } from 'react';
import { login } from '../api/auth';

export default function LoginForm({ setIsLoggedIn, setLoggedInUsername }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await login(username, password);
            setIsLoggedIn(true);
            setLoggedInUsername(username);
        } catch (error) {
            setError(error.message);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="username">Käyttäjänimi: </label>
                <input
                    type="text"
                    name="username"
                    id="username"
                    required
                    onChange={(event) => setUsername(event.target.value)}
                />
            </div>
            <div>
                <label htmlFor="password">Salasana: </label>
                <input
                    type="password"
                    name="password"
                    id="password"
                    required
                    onChange={(event) => setPassword(event.target.value)}
                />
            </div>
            <button type="submit">Login</button>
            {error && <div>{error}</div>}
        </form>
    )
}
