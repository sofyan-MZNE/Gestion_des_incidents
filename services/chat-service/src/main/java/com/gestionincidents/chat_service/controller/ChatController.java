package com.gestionincidents.chat_service.controller;

import com.gestionincidents.chat_service.model.*;
import com.gestionincidents.chat_service.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // POST créer une nouvelle conversation
    @PostMapping("/conversations")
    public ResponseEntity<Conversation> createConversation(@RequestParam UUID utilisateurId) {
        Conversation conversation = chatService.createConversation(utilisateurId);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
    }

    // GET récupérer une conversation par ID
    @GetMapping("/conversations/{id}")
    public ResponseEntity<Conversation> getConversation(@PathVariable UUID id) {
        return ResponseEntity.ok(chatService.getConversationById(id));
    }

    // GET toutes les conversations d'un utilisateur
    @GetMapping("/conversations/utilisateur/{utilisateurId}")
    public List<Conversation> getConversationsByUtilisateur(@PathVariable UUID utilisateurId) {
        return chatService.getConversationsByUtilisateur(utilisateurId);
    }

    // POST ajouter un message utilisateur
    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<ChatMessage> addMessage(
            @PathVariable UUID conversationId,
            @RequestParam String message,
            @RequestParam ChatMessage.Expediteur expediteur) {
        ChatMessage chatMessage = chatService.addMessage(conversationId, message, expediteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMessage);
    }

    // GET tous les messages d'une conversation
    @GetMapping("/conversations/{conversationId}/messages")
    public List<ChatMessage> getMessages(@PathVariable UUID conversationId) {
        return chatService.getMessagesByConversation(conversationId);
    }

    // POST ajouter une suggestion
    @PostMapping("/suggestions")
    public ResponseEntity<Suggestion> addSuggestion(
            @RequestParam UUID conversationId,
            @RequestParam UUID incidentId,
            @RequestParam Float score) {
        Suggestion suggestion = chatService.addSuggestion(conversationId, incidentId, score);
        return ResponseEntity.status(HttpStatus.CREATED).body(suggestion);
    }

    // PATCH accepter une suggestion
    @PatchMapping("/suggestions/{suggestionId}/accepter")
    public ResponseEntity<Suggestion> accepterSuggestion(@PathVariable UUID suggestionId) {
        return ResponseEntity.ok(chatService.accepterSuggestion(suggestionId));
    }

    // PATCH fermer une conversation avec création d'incident
    @PatchMapping("/conversations/{conversationId}/incident")
    public ResponseEntity<Conversation> fermerAvecIncident(
            @PathVariable UUID conversationId,
            @RequestParam String problemeResume) {
        return ResponseEntity.ok(chatService.fermerAvecIncident(conversationId, problemeResume));
    }
}