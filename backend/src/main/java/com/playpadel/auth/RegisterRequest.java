package com.playpadel.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "El nom és obligatori")
    @Size(max = 80)
    private String nom;

    @NotBlank(message = "Els cognoms són obligatoris")
    @Size(max = 120)
    private String cognoms;

    @NotBlank(message = "L'email és obligatori")
    @Email(message = "L'email no és vàlid")
    @Size(max = 254)
    private String email;

    @Pattern(regexp = "^$|^\\+?[0-9 ]{9,19}$", message = "El telèfon no és vàlid")
    private String telefon;

    @NotBlank(message = "La contrasenya és obligatòria")
    @Size(min = 8, max = 72, message = "La contrasenya ha de tenir entre 8 i 72 caràcters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
             message = "La contrasenya ha de tenir majúscula, minúscula i número")
    private String password;

    @AssertTrue(message = "Cal acceptar la política de privacitat")
    private boolean politicaAcceptada;

    private boolean acceptaComunicacions;

    public RegisterRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPoliticaAcceptada() {
        return politicaAcceptada;
    }

    public void setPoliticaAcceptada(boolean politicaAcceptada) {
        this.politicaAcceptada = politicaAcceptada;
    }

    public boolean isAcceptaComunicacions() {
        return acceptaComunicacions;
    }

    public void setAcceptaComunicacions(boolean acceptaComunicacions) {
        this.acceptaComunicacions = acceptaComunicacions;
    }

}