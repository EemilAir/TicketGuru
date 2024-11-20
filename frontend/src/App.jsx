import { useState } from "react";
import Login from "./components/Login";
import TicketSalesForm from "./components/TicketSalesForm";

const App = () => {
    const [auth, setAuth] = useState(null); // Tallennetaan autentikaatiotiedot

    const handleLogin = (credentials) => {
        const authString = btoa(`${credentials.username}:${credentials.password}`);
        setAuth(authString); // Tallenna base64-muodossa oleva autentikaatiotieto
        alert("Kirjautuminen onnistui!");
    };

    const handleLogout = () => {
        setAuth(null); // Poista autentikaatiotiedot
    };

    return (
        <div>
            {!auth ? (
                <Login onLogin={handleLogin} />
            ) : (
                <>
                    <button onClick={handleLogout}>Kirjaudu ulos</button>
                    <TicketSalesForm auth={auth} />
                </>
            )}
        </div>
    );
};

export default App;
