package com.gestionincidents.incident_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priorite priorite;

    // ID de l'utilisateur qui a créé l'incident
    @Column(name = "createur_id")
    private UUID createurId;

    // ID du technicien assigné
    @Column(name = "technicien_id")
    private UUID technicienId;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @Column(name = "date_resolution")
    private LocalDateTime dateResolution;

    @Column(name = "capture_url")
    private String captureUrl;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.statut = Statut.NOUVEAU;
    }

    @PreUpdate
    public void preUpdate() {
        this.dateModification = LocalDateTime.now();
    }

    // Les statuts possibles d'un incident
    public enum Statut {
        NOUVEAU,
        ASSIGNE,
        EN_COURS,
        RESOLU,
        FERME
    }

    // Les priorités possibles d'un incident
    public enum Priorite {
        FAIBLE,
        MOYENNE,
        HAUTE,
        CRITIQUE
    }
}