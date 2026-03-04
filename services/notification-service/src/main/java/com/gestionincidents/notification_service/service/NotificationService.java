package com.gestionincidents.notification_service.service;

import com.gestionincidents.notification_service.model.Notification;
import com.gestionincidents.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ObjectProvider<JavaMailSender> mailSenderProvider;

    // Créer et envoyer une notification
    public Notification envoyerNotification(UUID utilisateurId, String email,
                                            String sujet, String contenu,
                                            Notification.Type type) {
        // Créer la notification
        Notification notification = new Notification();
        notification.setUtilisateurId(utilisateurId);
        notification.setEmail(email);
        notification.setSujet(sujet);
        notification.setContenu(contenu);
        notification.setType(type);
        notification = notificationRepository.save(notification);

        JavaMailSender mailSender = mailSenderProvider.getIfAvailable();
        if (mailSender == null) {
            System.out.println("JavaMailSender non configure. Notification enregistree sans envoi email.");
            return notification;
        }

        // Envoyer l'email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(sujet);
            message.setText(contenu);
            mailSender.send(message);

            // Marquer comme envoyé
            notification.setEnvoye(true);
            notification.setDateEnvoi(LocalDateTime.now());
            notificationRepository.save(notification);

        } catch (Exception e) {
            System.out.println("Erreur envoi email: " + e.getMessage());
        }

        return notification;
    }

    // Récupérer toutes les notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Récupérer les notifications d'un utilisateur
    public List<Notification> getNotificationsByUtilisateur(UUID utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId);
    }

    // Récupérer les notifications non envoyées
    public List<Notification> getNotificationsNonEnvoyees() {
        return notificationRepository.findByEnvoye(false);
    }
}
