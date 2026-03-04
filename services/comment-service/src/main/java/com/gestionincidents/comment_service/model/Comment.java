package com.gestionincidents.comment_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // ID de l'incident concerné
    @Column(name = "incident_id", nullable = false)
    private UUID incidentId;

    // ID de l'utilisateur qui a écrit le commentaire
    @Column(name = "auteur_id", nullable = false)
    private UUID auteurId;

    // Le contenu du commentaire
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;

    // URL de la pièce jointe (optionnel)
    @Column(name = "piece_jointe_url")
    private String pieceJointeUrl;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }
}