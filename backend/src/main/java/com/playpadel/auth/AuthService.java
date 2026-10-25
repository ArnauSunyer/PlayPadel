package com.playpadel.auth;

import com.playpadel.user.User;
import com.playpadel.user.UserRepository;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registrar(RegisterRequest request) {
        String email = request.email().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aquest email ja està registrat");
        }

        User user = new User();
        user.setNom(request.nom().trim());
        user.setCognoms(request.cognoms().trim());
        user.setEmail(email);
        user.setTelefon(request.telefon() == null || request.telefon().isBlank()
                ? null : request.telefon().trim());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setPoliticaAcceptadaAt(Instant.now());
        user.setAcceptaComunicacions(request.acceptaComunicacions());

        return userRepository.save(user);
    }
}