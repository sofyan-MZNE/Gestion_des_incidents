package com.gestionincidents.comment_service.service;

import com.gestionincidents.comment_service.model.Comment;
import com.gestionincidents.comment_service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // Récupérer tous les commentaires
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Récupérer un commentaire par son ID
    public Optional<Comment> getCommentById(UUID id) {
        return commentRepository.findById(id);
    }

    // Récupérer tous les commentaires d'un incident
    public List<Comment> getCommentsByIncidentId(UUID incidentId) {
        return commentRepository.findByIncidentId(incidentId);
    }

    // Récupérer tous les commentaires d'un utilisateur
    public List<Comment> getCommentsByAuteurId(UUID auteurId) {
        return commentRepository.findByAuteurId(auteurId);
    }

    // Créer un commentaire
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Modifier un commentaire
    public Comment updateComment(UUID id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé"));
        comment.setContenu(commentDetails.getContenu());
        comment.setPieceJointeUrl(commentDetails.getPieceJointeUrl());
        return commentRepository.save(comment);
    }

    // Supprimer un commentaire
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}