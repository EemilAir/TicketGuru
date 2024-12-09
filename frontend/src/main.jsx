import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App.jsx';
import dayjs from 'dayjs';
import 'dayjs/locale/fi';
import { AuthProvider } from './components/AuthContext.jsx';


// Asetetaan dayjs käyttämään suomenkielistä localea
dayjs.locale('fi');

createRoot(document.getElementById('root')).render(
  <AuthProvider>
    <StrictMode>
      <App />
    </StrictMode>
  </AuthProvider >,
);