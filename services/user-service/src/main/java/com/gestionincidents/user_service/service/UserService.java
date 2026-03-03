package com.gestionincidents.user_service.service;

import com.gestionincidents.user_service.model.User;
import com.gestionincidents.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Créer un utilisateur
    public User createUser(User user) {
        user.setDateCreation(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Modifier un utilisateur utilisant ID
    public User updateUser(UUID id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setEmail(userDetails.getEmail());
        user.setDepartement(userDetails.getDepartement());
        user.setPoste(userDetails.getPoste());
        return userRepository.save(user);
    }

    // Supprimer un utilisateur utiliisant son ID
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}