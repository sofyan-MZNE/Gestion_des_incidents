import { AppBar, Toolbar, Typography, Button, Box, IconButton, Tooltip, Avatar } from '@mui/material';
import { useNavigate, useLocation } from 'react-router-dom';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import LightModeIcon from '@mui/icons-material/LightMode';
import DashboardIcon from '@mui/icons-material/Dashboard';
import BugReportIcon from '@mui/icons-material/BugReport';
import PeopleIcon from '@mui/icons-material/People';
import LogoutIcon from '@mui/icons-material/Logout';
import ShieldIcon from '@mui/icons-material/Shield';

function Navbar({ toggleDarkMode, darkMode }) {
  const navigate = useNavigate();
  const location = useLocation();

  const navItems = [
    { label: 'Dashboard', path: '/dashboard', icon: <DashboardIcon fontSize="small" /> },
    { label: 'Incidents', path: '/incidents', icon: <BugReportIcon fontSize="small" /> },
    { label: 'Utilisateurs', path: '/utilisateurs', icon: <PeopleIcon fontSize="small" /> },
  ];

  return (
    <AppBar position="sticky" elevation={0} sx={{
      background: darkMode
        ? 'linear-gradient(90deg, #0a0e1a 0%, #111827 100%)'
        : 'linear-gradient(90deg, #1565c0 0%, #1976d2 100%)',
      borderBottom: '1px solid rgba(33,150,243,0.2)',
    }}>
      <Toolbar sx={{ justifyContent: 'space-between' }}>

        {/* Logo */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
          <ShieldIcon sx={{ color: '#2196f3', fontSize: 30 }} />
          <Typography variant="h6" sx={{ fontWeight: 700, color: 'white' }}>
            Gestion des Incidents
          </Typography>
        </Box>

        {/* Navigation */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
          {navItems.map((item) => (
            <Button
              key={item.path}
              startIcon={item.icon}
              onClick={() => navigate(item.path)}
              sx={{
                color: location.pathname === item.path ? '#2196f3' : 'rgba(255,255,255,0.7)',
                backgroundColor: location.pathname === item.path ? 'rgba(33,150,243,0.1)' : 'transparent',
                borderRadius: 2,
                px: 2,
                textTransform: 'none',
                fontWeight: location.pathname === item.path ? 600 : 400,
                border: location.pathname === item.path ? '1px solid rgba(33,150,243,0.3)' : '1px solid transparent',
                '&:hover': {
                  backgroundColor: 'rgba(33,150,243,0.1)',
                  color: '#2196f3',
                },
              }}>
              {item.label}
            </Button>
          ))}

          {/* Toggle Dark/Light */}
          <Tooltip title={darkMode ? "Mode Clair" : "Mode Sombre"}>
            <IconButton onClick={toggleDarkMode} sx={{
              color: 'white',
              backgroundColor: 'rgba(33,150,243,0.1)',
              border: '1px solid rgba(33,150,243,0.3)',
              '&:hover': { backgroundColor: 'rgba(33,150,243,0.2)' },
            }}>
              {darkMode ? <LightModeIcon /> : <DarkModeIcon />}
            </IconButton>
          </Tooltip>

          {/* Déconnexion */}
          <Button
            startIcon={<LogoutIcon />}
            onClick={() => navigate('/')}
            sx={{
              color: 'rgba(255,255,255,0.7)',
              textTransform: 'none',
              '&:hover': { color: '#ef5350' },
            }}>
            Déconnexion
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;