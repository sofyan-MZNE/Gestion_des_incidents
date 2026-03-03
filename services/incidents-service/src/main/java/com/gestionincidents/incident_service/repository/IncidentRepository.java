package com.gestionincidents.incident_service.repository;

import com.gestionincidents.incident_service.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    // Chercher les incidents par statut
    List<Incident> findByStatut(Incident.Statut statut);

    // Chercher les incidents par priorité
    List<Incident> findByPriorite(Incident.Priorite priorite);

    // Chercher les incidents d'un utilisateur
    List<Incident> findByCreateurId(UUID createurId);

    // Chercher les incidents assignés à un technicien
    List<Incident> findByTechnicienId(UUID technicienId);
}