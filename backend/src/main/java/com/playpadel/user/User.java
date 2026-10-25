package com.playpadel.user;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "usuaris")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nom;

    @Column(nullable = false, length = 120)
    private String cognoms;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(length = 20)
    private String telefon;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role rol = Role.USER;

    @Column(nullable = false)
    private boolean actiu = true;

    @Column(name = "politica_acceptada_at", nullable = false)
    private Instant politicaAcceptadaAt;

    @Column(name = "accepta_comunicacions", nullable = false)
    private boolean acceptaComunicacions = false;

    @Column(name = "creat_at", nullable = false, updatable = false)
    private Instant creatAt;

    public User() {
    }

    @PrePersist
    void alCrear() {
        if (creatAt == null) {
            creatAt = Instant.now();
        }
    }

    //GETTERS 

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRol() {
        return rol;
    }

    public boolean isActiu() {
        return actiu;
    }

    public Instant getPoliticaAcceptadaAt() {
        return politicaAcceptadaAt;
    }

    public boolean isAcceptaComunicacions() {
        return acceptaComunicacions;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    // SETTERS 
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public void setPoliticaAcceptadaAt(Instant politicaAcceptadaAt) {
        this.politicaAcceptadaAt = politicaAcceptadaAt;
    }

    public void setAcceptaComunicacions(boolean acceptaComunicacions) {
        this.acceptaComunicacions = acceptaComunicacions;
    }

    

}

