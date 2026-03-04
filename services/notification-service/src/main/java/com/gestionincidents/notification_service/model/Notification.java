package com.gestionincidents.notification_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ID de l'utilisateur qui reçoit la notification
    @Column(name = "utilisateur_id", nullable = false)
    private UUID utilisateurId;

    // Email du destinataire
    @Column(nullable = false)
    private String email;

    // Sujet de la notification
    @Column(nullable = false)
    private String sujet;

    // Contenu de la notification
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;

    // Type de notification
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    // Est-ce que la notification a été envoyée ?
    @Column(nullable = false)
    private Boolean envoye;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_envoi")
    private LocalDateTime dateEnvoi;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.envoye = false;
    }

    public enum Type {
        INCIDENT_CREE,
        INCIDENT_ASSIGNE,
        STATUT_CHANGE,
        COMMENTAIRE_AJOUTE
    }
}