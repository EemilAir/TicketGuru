import { useState } from 'react';
import { login } from '../api/auth';
import '../css/LoginForm.css';

export default function LoginForm({ handleLoginSuccess, setError }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await login(username, password);
            handleLoginSuccess(username);
        } catch (error) {
            setError(error.message);
        }
    }

    return (
        <form className="login-form" onSubmit={handleSubmit}>
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
        </form>
    )
}