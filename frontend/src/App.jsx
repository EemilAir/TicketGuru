import { useState, useEffect } from 'react'
import { getToken, getUsername, removeToken } from './utils/storage';
import './App.css'

import LoginForm from './components/LoginForm';
import Liput from './components/Liput';

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');
  const [loggedInUsername, setLoggedInUsername] = useState('');
  const [liput, setLiput] = useState([]);

  useEffect(() => {
    const token = getToken();
    const username = getUsername();
    if (token) {
      setIsLoggedIn(true);
      setLoggedInUsername(username);
    }
  }, []);

  const handleLogout = () => {
    removeToken();
    setIsLoggedIn(false);
  }

  return (
    <>
      {isLoggedIn ? (
        <>
          <h1>Logged In as {loggedInUsername} <button onClick={handleLogout}>Logout</button></h1>
          {error && <div style={{color: "red"}}>{error}</div>}
          {success && <div style={{color: "green"}}>{success}</div>}
          {liput && <Liput liput={liput} setLiput={setLiput} setError={setError} setSuccess={setSuccess} />}
        </>
      ) : (
        <>
          <h1>Login</h1>
          <LoginForm setIsLoggedIn={setIsLoggedIn} setLoggedInUsername={setLoggedInUsername} />
        </>
      )}
    </>
  )
}

export default App
