package com.gestionincidents.notification_service.controller;

import com.gestionincidents.notification_service.model.Notification;
import com.gestionincidents.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // GET toutes les notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // GET notifications d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Notification> getByUtilisateur(@PathVariable UUID utilisateurId) {
        return notificationService.getNotificationsByUtilisateur(utilisateurId);
    }

    // GET notifications non envoyées
    @GetMapping("/non-envoyees")
    public List<Notification> getNonEnvoyees() {
        return notificationService.getNotificationsNonEnvoyees();
    }

    // POST envoyer une notification
    @PostMapping("/envoyer")
    public ResponseEntity<Notification> envoyerNotification(
            @RequestParam UUID utilisateurId,
            @RequestParam String email,
            @RequestParam String sujet,
            @RequestParam String contenu,
            @RequestParam Notification.Type type) {
        Notification notification = notificationService.envoyerNotification(
                utilisateurId, email, sujet, contenu, type);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}