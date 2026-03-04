package com.gestionincidents.chat_service.repository;

import com.gestionincidents.chat_service.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    // Récupérer toutes les conversations d'un utilisateur
    List<Conversation> findByUtilisateurId(UUID utilisateurId);

    // Récupérer les conversations par statut
    List<Conversation> findByStatut(Conversation.Statut statut);
}