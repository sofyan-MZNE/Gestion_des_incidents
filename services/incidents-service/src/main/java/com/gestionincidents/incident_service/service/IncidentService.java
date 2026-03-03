package com.gestionincidents.incident_service.service;

import com.gestionincidents.incident_service.model.Incident;
import com.gestionincidents.incident_service.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    // Récupérer tous les incidents
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Récupérer un incident par ID
    public Optional<Incident> getIncidentById(UUID id) {
        return incidentRepository.findById(id);
    }

    // Créer un incident
    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    // Modifier un incident
    public Incident updateIncident(UUID id, Incident incidentDetails) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));
        incident.setTitre(incidentDetails.getTitre());
        incident.setDescription(incidentDetails.getDescription());
        incident.setPriorite(incidentDetails.getPriorite());
        incident.setStatut(incidentDetails.getStatut());
        incident.setTechnicienId(incidentDetails.getTechnicienId());
        return incidentRepository.save(incident);
    }

    // Changer le statut d'un incident
    public Incident changerStatut(UUID id, Incident.Statut statut) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident non trouvé"));
        incident.setStatut(statut);
        if (statut == Incident.Statut.RESOLU) {
            incident.setDateResolution(LocalDateTime.now());
        }
        return incidentRepository.save(incident);
    }

    // Supprimer un incident
    public void deleteIncident(UUID id) {
        incidentRepository.deleteById(id);
    }

    // Récupérer les incidents par statut
    public List<Incident> getIncidentsByStatut(Incident.Statut statut) {
        return incidentRepository.findByStatut(statut);
    }

    // Récupérer les incidents par priorité
    public List<Incident> getIncidentsByPriorite(Incident.Priorite priorite) {
        return incidentRepository.findByPriorite(priorite);
    }

    // Récupérer les incidents d'un utilisateur
    public List<Incident> getIncidentsByCreateur(UUID createurId) {
        return incidentRepository.findByCreateurId(createurId);
    }

    // Récupérer les incidents d'un technicien
    public List<Incident> getIncidentsByTechnicien(UUID technicienId) {
        return incidentRepository.findByTechnicienId(technicienId);
    }
}