import { useState, useEffect } from 'react';
import { Container, Grid, Paper, Typography, Box, Divider } from '@mui/material';
import BugReportIcon from '@mui/icons-material/BugReport';
import PeopleIcon from '@mui/icons-material/People';
import NotificationsIcon from '@mui/icons-material/Notifications';
import TrendingUpIcon from '@mui/icons-material/TrendingUp';
import Navbar from '../components/Navbar';
import { incidentService, userService, notificationService } from '../services/api';

function Dashboard({ toggleDarkMode, darkMode }) {
  const [stats, setStats] = useState({
    totalIncidents: 0,
    totalUtilisateurs: 0,
    totalNotifications: 0,
  });

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const incidents = await incidentService.getAll();
        const utilisateurs = await userService.getAll();
        const notifications = await notificationService.getAll();
        setStats({
          totalIncidents: incidents.data.length,
          totalUtilisateurs: utilisateurs.data.length,
          totalNotifications: notifications.data.length,
        });
      } catch (error) {
        console.log('Erreur:', error);
      }
    };
    fetchStats();
  }, []);

  const cards = [
    {
      titre: 'Total Incidents',
      valeur: stats.totalIncidents,
      icon: <BugReportIcon sx={{ fontSize: 40 }} />,
      color: '#ef5350',
      gradient: 'linear-gradient(135deg, #ef5350, #b71c1c)',
    },
    {
      titre: 'Utilisateurs',
      valeur: stats.totalUtilisateurs,
      icon: <PeopleIcon sx={{ fontSize: 40 }} />,
      color: '#2196f3',
      gradient: 'linear-gradient(135deg, #2196f3, #0d47a1)',
    },
    {
      titre: 'Notifications',
      valeur: stats.totalNotifications,
      icon: <NotificationsIcon sx={{ fontSize: 40 }} />,
      color: '#4caf50',
      gradient: 'linear-gradient(135deg, #4caf50, #1b5e20)',
    },
    {
      titre: 'Activité',
      valeur: '↑ 12%',
      icon: <TrendingUpIcon sx={{ fontSize: 40 }} />,
      color: '#ff9800',
      gradient: 'linear-gradient(135deg, #ff9800, #e65100)',
    },
  ];

  return (
    <>
      <Navbar toggleDarkMode={toggleDarkMode} darkMode={darkMode} />
      <Box sx={{ minHeight: '100vh', py: 4, px: 3 }}>
        <Container maxWidth="lg">

          {/* Header */}
          <Box className="fade-in" sx={{ mb: 4 }}>
            <Typography variant="h4" sx={{ fontWeight: 700, color: '#2196f3' }}>
              📊 Dashboard Admin
            </Typography>
            <Typography variant="body1" sx={{ color: 'grey.500', mt: 0.5 }}>
              Bienvenue ! Voici un aperçu de votre système.
            </Typography>
            <Divider sx={{ mt: 2, borderColor: 'rgba(33,150,243,0.2)' }} />
          </Box>

          {/* Stat Cards */}
          <Grid container spacing={3}>
            {cards.map((card, index) => (
              <Grid item xs={12} sm={6} md={3} key={index}>
                <Paper className="stat-card fade-in" elevation={0} sx={{
                  p: 3, borderRadius: 3,
                  border: '1px solid rgba(33,150,243,0.1)',
                  background: darkMode ? '#111827' : '#ffffff',
                  animationDelay: `${index * 0.1}s`,
                }}>
                  <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
                    <Box>
                      <Typography variant="body2" sx={{ color: 'grey.500', mb: 1 }}>
                        {card.titre}
                      </Typography>
                      <Typography variant="h3" sx={{ fontWeight: 700, color: card.color }}>
                        {card.valeur}
                      </Typography>
                    </Box>
                    <Box sx={{
                      p: 1.5, borderRadius: 2,
                      background: card.gradient,
                      color: 'white',
                      boxShadow: `0 8px 20px ${card.color}40`,
                    }}>
                      {card.icon}
                    </Box>
                  </Box>
                </Paper>
              </Grid>
            ))}
          </Grid>

        </Container>
      </Box>
    </>
  );
}

export default Dashboard;