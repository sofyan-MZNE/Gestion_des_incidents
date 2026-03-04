import { useState, useEffect } from 'react';
import {
  Container, Typography, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper, Box, Avatar, Divider
} from '@mui/material';
import PeopleIcon from '@mui/icons-material/People';
import Navbar from '../components/Navbar';
import { userService } from '../services/api';

function Utilisateurs({ toggleDarkMode, darkMode }) {
  const [utilisateurs, setUtilisateurs] = useState([]);

  useEffect(() => { fetchUtilisateurs(); }, []);

  const fetchUtilisateurs = async () => {
    try {
      const response = await userService.getAll();
      setUtilisateurs(response.data);
    } catch (error) {
      console.log('Erreur:', error);
    }
  };

  return (
    <>
      <Navbar toggleDarkMode={toggleDarkMode} darkMode={darkMode} />
      <Box sx={{ minHeight: '100vh', py: 4, px: 3 }}>
        <Container maxWidth="lg">

          <Box className="fade-in" sx={{ mb: 4 }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
              <PeopleIcon sx={{ color: '#2196f3', fontSize: 35 }} />
              <Typography variant="h4" sx={{ fontWeight: 700, color: '#2196f3' }}>
                Gestion des Utilisateurs
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
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Avatar</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Nom</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Prénom</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Email</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Département</TableCell>
                  <TableCell sx={{ fontWeight: 700, color: '#2196f3' }}>Poste</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {utilisateurs.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={6} align="center" sx={{ py: 5, color: 'grey.500' }}>
                      Aucun utilisateur trouvé
                    </TableCell>
                  </TableRow>
                ) : (
                  utilisateurs.map((user) => (
                    <TableRow key={user.id} hover sx={{
                      '&:hover': { backgroundColor: 'rgba(33,150,243,0.05)' }
                    }}>
                      <TableCell>
                        <Avatar sx={{
                          background: 'linear-gradient(135deg, #2196f3, #0d47a1)',
                          fontWeight: 700,
                        }}>
                          {user.nom?.charAt(0)}{user.prenom?.charAt(0)}
                        </Avatar>
                      </TableCell>
                      <TableCell sx={{ fontWeight: 500 }}>{user.nom}</TableCell>
                      <TableCell>{user.prenom}</TableCell>
                      <TableCell sx={{ color: '#2196f3' }}>{user.email}</TableCell>
                      <TableCell>{user.departement}</TableCell>
                      <TableCell>{user.poste}</TableCell>
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

export default Utilisateurs;