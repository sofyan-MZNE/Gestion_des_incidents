import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Services Utilisateurs
export const userService = {
  getAll: () => api.get('/api/users'),
  getById: (id) => api.get(`/api/users/${id}`),
  create: (data) => api.post('/api/users', data),
  update: (id, data) => api.put(`/api/users/${id}`, data),
  delete: (id) => api.delete(`/api/users/${id}`),
};

// Services Incidents
export const incidentService = {
  getAll: () => api.get('/api/incidents'),
  getById: (id) => api.get(`/api/incidents/${id}`),
  create: (data) => api.post('/api/incidents', data),
  update: (id, data) => api.put(`/api/incidents/${id}`, data),
  delete: (id) => api.delete(`/api/incidents/${id}`),
};

// Services Notifications
export const notificationService = {
  getAll: () => api.get('/api/notifications'),
  getByUtilisateur: (id) => api.get(`/api/notifications/utilisateur/${id}`),
};

export default api;