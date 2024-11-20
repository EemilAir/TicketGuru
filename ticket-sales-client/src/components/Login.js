import React, { useState } from "react";

const Login = ({ onLogin }) => {
    const [credentials, setCredentials] = useState({ username: "", password: "" });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (credentials.username && credentials.password) {
            onLogin(credentials); // Välitä kirjautumistiedot pääkomponentille
        } else {
            alert("Täytä käyttäjätunnus ja salasana!");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Käyttäjätunnus:</label>
                <input
                    type="text"
                    name="username"
                    value={credentials.username}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Salasana:</label>
                <input
                    type="password"
                    name="password"
                    value={credentials.password}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Kirjaudu</button>
        </form>
    );
};

export default Login;