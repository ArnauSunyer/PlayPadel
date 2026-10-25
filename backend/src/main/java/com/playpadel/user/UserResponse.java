package com.playpadel.user;

public record UserResponse(Long id, String nom, String cognoms, String email, String rol) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getNom(),
                user.getCognoms(),
                user.getEmail(),
                user.getRol().name()
        );
    }
}