package com.playpadel.auth;

import com.playpadel.security.JwtService;
import com.playpadel.user.User;
import com.playpadel.user.UserRepository;
import com.playpadel.user.UserResponse;
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
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public User registrar(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aquest email ja està registrat");
        }

        User user = new User();
        user.setNom(request.getNom().trim());
        user.setCognoms(request.getCognoms().trim());
        user.setEmail(email);
        user.setTelefon(request.getTelefon() == null || request.getTelefon().isBlank()
                ? null : request.getTelefon().trim());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPoliticaAcceptadaAt(Instant.now());
        user.setAcceptaComunicacions(request.isAcceptaComunicacions());

        return userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Email o contrasenya incorrectes"));

        if (!user.isActiu()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Aquest compte està desactivat");
        }

        String token = jwtService.generarToken(user);
        return new AuthResponse(token, "Bearer", jwtService.getExpiracioSegons(), UserResponse.from(user));
    }
}