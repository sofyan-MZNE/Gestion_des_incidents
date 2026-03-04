package com.gestionincidents.chat_service.repository;

import com.gestionincidents.chat_service.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, UUID> {

    // Récupérer toutes les suggestions d'une conversation
    List<Suggestion> findByConversationId(UUID conversationId);
}