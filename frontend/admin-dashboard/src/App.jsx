import { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme, CssBaseline } from '@mui/material';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Incidents from './pages/Incidents';
import Utilisateurs from './pages/Utilisateurs';
import './index.css';

function App() {
  const [darkMode, setDarkMode] = useState(true);

  const theme = createTheme({
    palette: {
      mode: darkMode ? 'dark' : 'light',
      primary: { main: '#2196f3' },
      secondary: { main: '#90caf9' },
      background: {
        default: darkMode ? '#0a0e1a' : '#f5f7ff',
        paper: darkMode ? '#111827' : '#ffffff',
      },
    },
    typography: { fontFamily: "'Poppins', sans-serif" },
    shape: { borderRadius: 12 },
  });

  const toggleDarkMode = () => setDarkMode(!darkMode);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login toggleDarkMode={toggleDarkMode} darkMode={darkMode} />} />
          <Route path="/dashboard" element={<Dashboard toggleDarkMode={toggleDarkMode} darkMode={darkMode} />} />
          <Route path="/incidents" element={<Incidents toggleDarkMode={toggleDarkMode} darkMode={darkMode} />} />
          <Route path="/utilisateurs" element={<Utilisateurs toggleDarkMode={toggleDarkMode} darkMode={darkMode} />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;