package com.gestionincidents.notification_service.repository;

import com.gestionincidents.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // Récupérer toutes les notifications d'un utilisateur
    List<Notification> findByUtilisateurId(UUID utilisateurId);

    // Récupérer les notifications non envoyées
    List<Notification> findByEnvoye(Boolean envoye);

    // Récupérer les notifications par type
    List<Notification> findByType(Notification.Type type);
}