package com.gestionincidents.comment_service.controller;

import com.gestionincidents.comment_service.model.Comment;
import com.gestionincidents.comment_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // GET tous les commentaires
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // GET un commentaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable UUID id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET commentaires d'un incident
    @GetMapping("/incident/{incidentId}")
    public List<Comment> getCommentsByIncident(@PathVariable UUID incidentId) {
        return commentService.getCommentsByIncidentId(incidentId);
    }

    // GET commentaires d'un utilisateur
    @GetMapping("/auteur/{auteurId}")
    public List<Comment> getCommentsByAuteur(@PathVariable UUID auteurId) {
        return commentService.getCommentsByAuteurId(auteurId);
    }

    // POST créer un commentaire
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment created = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT modifier un commentaire
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable UUID id,
            @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    // DELETE supprimer un commentaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}