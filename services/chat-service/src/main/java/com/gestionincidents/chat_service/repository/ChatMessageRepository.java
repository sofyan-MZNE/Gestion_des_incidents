package com.gestionincidents.chat_service.repository;

import com.gestionincidents.chat_service.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {

    // Récupérer tous les messages d'une conversation
    List<ChatMessage> findByConversationIdOrderByTimestampAsc(UUID conversationId);
}