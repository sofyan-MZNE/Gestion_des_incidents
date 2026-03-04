package com.gestionincidents.chat_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "suggestions")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;

    // ID de l'incident similaire trouvé
    @Column(name = "incident_similaire_id")
    private UUID incidentSimilaireId;

    // Score de similarité entre 0 et 1
    @Column(name = "score_similarite")
    private Float scoreSimilarite;

    // Est-ce que l'utilisateur a accepté cette suggestion ?
    private Boolean accepte;
}