package com.gestionincidents.comment_service.repository;

import com.gestionincidents.comment_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    // Récupérer tous les commentaires d'un incident
    List<Comment> findByIncidentId(UUID incidentId);

    // Récupérer tous les commentaires d'un utilisateur
    List<Comment> findByAuteurId(UUID auteurId);
}