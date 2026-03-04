import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Box, Typography, TextField, Button, Alert, IconButton, Tooltip
} from '@mui/material';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import LightModeIcon from '@mui/icons-material/LightMode';

function Login({ toggleDarkMode, darkMode }) {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = () => {
    if (email === 'sofyan@gmail.com' && password === 'sof2004') {
      navigate('/dashboard');
    } else {
      setError('Email ou mot de passe incorrect !');
    }
  };

  return (
    <Box className="gradient-bg" sx={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      minHeight: '100vh',
      position: 'relative',
    }}>

      {/* Bouton dark mode */}
      <Tooltip title={darkMode ? "Mode Clair" : "Mode Sombre"}>
        <IconButton onClick={toggleDarkMode} sx={{
          position: 'absolute', top: 20, right: 20,
          color: 'white', backgroundColor: 'rgba(255,255,255,0.2)',
          '&:hover': { backgroundColor: 'rgba(255,255,255,0.3)' }
        }}>
          {darkMode ? <LightModeIcon /> : <DarkModeIcon />}
        </IconButton>
      </Tooltip>

      {/* Cercles décoratifs */}
      <Box sx={{
        position: 'absolute', top: -100, left: -100,
        width: 400, height: 400, borderRadius: '50%',
        background: 'rgba(255,255,255,0.05)',
      }} />
      <Box sx={{
        position: 'absolute', bottom: -100, right: -100,
        width: 500, height: 500, borderRadius: '50%',
        background: 'rgba(255,255,255,0.05)',
      }} />

      {/* Card Login */}
      <Box className="glass-card fade-in" sx={{
        padding: 5, width: 420,
        background: 'rgba(255,255,255,0.15)',
        backdropFilter: 'blur(20px)',
        border: '1px solid rgba(255,255,255,0.3)',
      }}>

        {/* Logo */}
        <Box sx={{ textAlign: 'center', mb: 4 }}>
          <Typography variant="h2" sx={{ mb: 1 }}>🛡️</Typography>
          <Typography variant="h4" className="neon-text"
            sx={{ color: 'white', fontWeight: 700 }}>
            Gestion des Incidents
          </Typography>
          <Typography variant="body1" sx={{ color: 'rgba(255,255,255,0.7)', mt: 1 }}>
            Connectez-vous à votre espace admin
          </Typography>
        </Box>

        {error && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }}>{error}</Alert>}

        <TextField
          fullWidth
          label="Email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          sx={{
            mb: 2,
            '& .MuiOutlinedInput-root': {
              borderRadius: 3,
              color: 'white',
              '& fieldset': { borderColor: 'rgba(255,255,255,0.3)' },
              '&:hover fieldset': { borderColor: 'white' },
            },
            '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' },
          }}
        />

        <TextField
          fullWidth
          label="Mot de passe"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          sx={{
            mb: 3,
            '& .MuiOutlinedInput-root': {
              borderRadius: 3,
              color: 'white',
              '& fieldset': { borderColor: 'rgba(255,255,255,0.3)' },
              '&:hover fieldset': { borderColor: 'white' },
            },
            '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' },
          }}
        />

        <Button
          fullWidth
          variant="contained"
          size="large"
          onClick={handleLogin}
          className="pulse"
          sx={{
            borderRadius: 3,
            py: 1.5,
            background: 'linear-gradient(135deg, #6c63ff, #ff6584)',
            fontSize: '1rem',
            fontWeight: 600,
            textTransform: 'none',
            boxShadow: '0 10px 30px rgba(108,99,255,0.4)',
            '&:hover': {
              background: 'linear-gradient(135deg, #5a52d5, #e55070)',
              boxShadow: '0 15px 40px rgba(108,99,255,0.6)',
              transform: 'translateY(-2px)',
            },
          }}>
          Se connecter
        </Button>

      </Box>
    </Box>
  );
}

export default Login;