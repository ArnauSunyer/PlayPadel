package com.playpadel.user;

public class UserResponse {

    private final Long id;
    private final String nom;
    private final String cognoms;
    private final String email;
    private final String rol;

    public UserResponse(Long id, String nom, String cognoms, String email, String rol) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.rol = rol;
    }

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getNom(),
                user.getCognoms(),
                user.getEmail(),
                user.getRol().name()
        );
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getCognoms() { return cognoms; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}