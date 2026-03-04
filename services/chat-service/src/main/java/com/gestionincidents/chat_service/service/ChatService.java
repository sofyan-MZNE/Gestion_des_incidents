package com.gestionincidents.chat_service.service;

import com.gestionincidents.chat_service.model.*;
import com.gestionincidents.chat_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ConversationRepository conversationRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SuggestionRepository suggestionRepository;

    // Créer une nouvelle conversation
    public Conversation createConversation(UUID utilisateurId) {
        Conversation conversation = new Conversation();
        conversation.setUtilisateurId(utilisateurId);
        return conversationRepository.save(conversation);
    }

    // Récupérer une conversation par ID
    public Conversation getConversationById(UUID id) {
        return conversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation non trouvée"));
    }

    // Récupérer toutes les conversations d'un utilisateur
    public List<Conversation> getConversationsByUtilisateur(UUID utilisateurId) {
        return conversationRepository.findByUtilisateurId(utilisateurId);
    }

    // Ajouter un message à une conversation
    public ChatMessage addMessage(UUID conversationId, String message, ChatMessage.Expediteur expediteur) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setConversationId(conversationId);
        chatMessage.setMessage(message);
        chatMessage.setExpediteur(expediteur);
        return chatMessageRepository.save(chatMessage);
    }

    // Récupérer tous les messages d'une conversation
    public List<ChatMessage> getMessagesByConversation(UUID conversationId) {
        return chatMessageRepository.findByConversationIdOrderByTimestampAsc(conversationId);
    }

    // Ajouter une suggestion
    public Suggestion addSuggestion(UUID conversationId, UUID incidentId, Float score) {
        Suggestion suggestion = new Suggestion();
        suggestion.setConversationId(conversationId);
        suggestion.setIncidentSimilaireId(incidentId);
        suggestion.setScoreSimilarite(score);
        suggestion.setAccepte(false);
        return suggestionRepository.save(suggestion);
    }

    // Accepter une suggestion
    public Suggestion accepterSuggestion(UUID suggestionId) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new RuntimeException("Suggestion non trouvée"));
        suggestion.setAccepte(true);
        // Marquer la conversation comme résolue
        Conversation conversation = conversationRepository.findById(suggestion.getConversationId())
                .orElseThrow(() -> new RuntimeException("Conversation non trouvée"));
        conversation.setStatut(Conversation.Statut.RESOLU);
        conversationRepository.save(conversation);
        return suggestionRepository.save(suggestion);
    }

    // Fermer une conversation avec création d'incident
    public Conversation fermerAvecIncident(UUID conversationId, String problemeResume) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation non trouvée"));
        conversation.setStatut(Conversation.Statut.INCIDENT_CREE);
        conversation.setProblemeResume(problemeResume);
        return conversationRepository.save(conversation);
    }
}