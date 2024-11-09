import './App.css';
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
    <>
      {isLoggedIn ?
        (
          <h1>Logged In as {username} <button onClick={handleLogout}>Logout</button></h1>
        ) : (
          <h1>Login</h1>
        )}
      {error && <div className="error" style={{color: "red"}}>{error}</div>}
      {success && <div className="success" style={{color: "green"}}>{success}</div>}
      {isLoggedIn ? 
      (
        <Liput liput={liput} setLiput={setLiput} setError={setErrorMessage} setSuccess={setSuccessMessage} />
      ) 
      : 
      (
        <LoginForm handleLoginSuccess={handleLoginSuccess} setError={setErrorMessage} />
      )}
    </>
  );
}

export default App;
