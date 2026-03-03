package com.gestionincidents.incident_service.controller;

import com.gestionincidents.incident_service.model.Incident;
import com.gestionincidents.incident_service.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    // GET tous les incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // GET un incident par ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable UUID id) {
        return incidentService.getIncidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST créer un incident
    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        Incident created = incidentService.createIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT modifier un incident
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(
            @PathVariable UUID id,
            @RequestBody Incident incident) {
        return ResponseEntity.ok(incidentService.updateIncident(id, incident));
    }

    // PATCH changer le statut d'un incident
    @PatchMapping("/{id}/statut")
    public ResponseEntity<Incident> changerStatut(
            @PathVariable UUID id,
            @RequestParam Incident.Statut statut) {
        return ResponseEntity.ok(incidentService.changerStatut(id, statut));
    }

    // DELETE supprimer un incident
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable UUID id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    // GET incidents par statut
    @GetMapping("/statut/{statut}")
    public List<Incident> getByStatut(@PathVariable Incident.Statut statut) {
        return incidentService.getIncidentsByStatut(statut);
    }

    // GET incidents par priorité
    @GetMapping("/priorite/{priorite}")
    public List<Incident> getByPriorite(@PathVariable Incident.Priorite priorite) {
        return incidentService.getIncidentsByPriorite(priorite);
    }

    // GET incidents d'un utilisateur
    @GetMapping("/createur/{createurId}")
    public List<Incident> getByCreateur(@PathVariable UUID createurId) {
        return incidentService.getIncidentsByCreateur(createurId);
    }

    // GET incidents d'un technicien
    @GetMapping("/technicien/{technicienId}")
    public List<Incident> getByTechnicien(@PathVariable UUID technicienId) {
        return incidentService.getIncidentsByTechnicien(technicienId);
    }
}