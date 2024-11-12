import './css/App.css';
import { useState, useEffect } from 'react';
import LoginForm from './components/LoginForm';
import Liput from './components/Liput';
import { getToken, getUsername, removeToken, removeUsername } from './utils/storage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState('');
  const [liput, setLiput] = useState([]);
  const [successTimeout, setSuccessTimeout] = useState(null);
  const [errorTimeout, setErrorTimeout] = useState(null);

  useEffect(() => {
    const token = getToken();
    const storedUsername = getUsername();
    if (token && storedUsername) {
      setIsLoggedIn(true);
      setUsername(storedUsername);
    }
  }, []);

  useEffect(() => {
    return () => {
      // Clear the timeouts when the component unmounts
      if (successTimeout) {
        clearTimeout(successTimeout);
      }
      if (errorTimeout) {
        clearTimeout(errorTimeout);
      }
    };
  }, [successTimeout, errorTimeout]);

  const handleLogout = () => {
    removeToken();
    removeUsername();
    setIsLoggedIn(false);
    setUsername('');
  };

  const handleLoginSuccess = (username) => {
    setIsLoggedIn(true);
    setUsername(username);
  };

  const setSuccessMessage = (message) => {
    setSuccess(message);
    if (successTimeout) {
      clearTimeout(successTimeout);
    }
    const timeout = setTimeout(() => {
      setSuccess('');
      setSuccessTimeout(null); // Clear the timeout state
    }, 5000);
    setSuccessTimeout(timeout);
  };

  const setErrorMessage = (message) => {
    setError(message);
    if (errorTimeout) {
      clearTimeout(errorTimeout);
    }
    const timeout = setTimeout(() => {
      setError('');
      setErrorTimeout(null); // Clear the timeout state
    }, 5000);
    setErrorTimeout(timeout);
  };

  return (
    <div className="app-container">
      <header className="app-header">
        {isLoggedIn ? (
          <div className="header-content">
            <h1>Welcome to Ticketguru, {username}</h1>
            <button className="logout-button" onClick={handleLogout}>Logout</button>
          </div>
        ) : (
          <h1>TicketGuru Login</h1>
        )}
      </header>
      <main className="app-main">
        {error && <div className="error-message">{error}</div>}
        {success && <div className="success-message">{success}</div>}
        {isLoggedIn ? (
          <Liput liput={liput} setLiput={setLiput} setError={setErrorMessage} setSuccess={setSuccessMessage} />
        ) : (
          <LoginForm handleLoginSuccess={handleLoginSuccess} setError={setErrorMessage} />
        )}
      </main>
    </div>
  );
}

export default App;
