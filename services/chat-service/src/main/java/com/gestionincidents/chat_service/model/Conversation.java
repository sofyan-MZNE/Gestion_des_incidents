package com.gestionincidents.chat_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "chat_conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "utilisateur_id", nullable = false)
    private UUID utilisateurId;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    @Column(name = "probleme_resume", columnDefinition = "TEXT")
    private String problemeResume;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.statut = Statut.EN_COURS;
    }

    public enum Statut {
        EN_COURS,
        RESOLU,
        INCIDENT_CREE
    }
}