import { useState, useEffect } from 'react';
import {
  Container, Typography, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper, Chip, Box, Divider
} from '@mui/material';
import BugReportIcon from '@mui/icons-material/BugReport';
import Navbar from '../components/Navbar';
import { incidentService } from '../services/api';

function Incidents({ toggleDarkMode, darkMode }) {
  const [incidents, setIncidents] = useState([]);

  useEffect(() => { fetchIncidents(); }, []);

  const fetchIncidents = async () => {
    try {
      const response = await incidentService.getAll();
      setIncidents(response.data);
    } catch (error) {
      console.log('Erreur:', error);
    }
  };

  const getStatutColor = (statut) => {
    switch (statut) {
      case 'NOUVEAU': return 'error';
      case 'EN_COURS': return 'warning';
      case 'RESOLU': return 'success';
      case 'FERME': return 'default';
      default: return 'default';
    }
  };

  const getPrioriteColor = (priorite) => {
    switch (priorite) {
      case 'HAUTE': return '#ef5350';
      case 'MOYENNE': return '#ff9800';
      case 'BASSE': return '#4caf50';
      default: return '#9e9e9e';
    }
  };

  return (
    <>
      <Navbar toggleDarkMode={toggleDarkMode} darkMode={darkMode} />
      <Box sx={{ minHeight: '100vh', py: 4, px: 3 }}>
        <Container maxWidth="lg">

          <Box className="fade-in" sx={{ mb: 4 }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
              <BugReportIcon sx={{ color: '#2196f3', fontSize: 35 }} />
              <Typography variant="h4" sx={{ fontWeight: 700, color: '#2196f3' }}>
                Gestion des Incidents
              </Typography>
            </Box>
            <Divider sx={{ mt: 2, borderColor: 'rgba(33,150,243,0.2)' }} />
          </Box>

          <TableContainer component={Paper} elevation={0} sx={{
            borderRadius: 3,
            border: '1px solid rgba(33,150,243,0.1)',
          }}>
            <Table>
              <TableHead>
                <TableRow sx={{ backgroundColor: 'rgba(33,150,243,0.1)' }}>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Titre</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Statut</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Priorité</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Date</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {incidents.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={4} align="center" sx={{ py: 5, color: 'grey.500' }}>
                      Aucun incident trouvé
                    </TableCell>
                  </TableRow>
                ) : (
                  incidents.map((incident) => (
                    <TableRow key={incident.id} hover sx={{
                      '&:hover': { backgroundColor: 'rgba(33,150,243,0.05)' }
                    }}>
                      <TableCell sx={{ fontWeight: 500 }}>{incident.titre}</TableCell>
                      <TableCell>
                        <Chip label={incident.statut} color={getStatutColor(incident.statut)} size="small" />
                      </TableCell>
                      <TableCell>
                        <Typography sx={{ color: getPrioriteColor(incident.priorite), fontWeight: 600 }}>
                          {incident.priorite}
                        </Typography>
                      </TableCell>
                      <TableCell sx={{ color: 'grey.500' }}>
                        {new Date(incident.dateCreation).toLocaleDateString('fr-FR')}
                      </TableCell>
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </TableContainer>

        </Container>
      </Box>
    </>
  );
}

export default Incidents;