package com.playpadel.auth;

import com.playpadel.user.UserResponse;

public class AuthResponse {

    private final String token;
    private final String tipus;
    private final long expiraEnSegons;
    private final UserResponse usuari;

    public AuthResponse(String token, String tipus, long expiraEnSegons, UserResponse usuari) {
        this.token = token;
        this.tipus = tipus;
        this.expiraEnSegons = expiraEnSegons;
        this.usuari = usuari;
    }

    public String getToken() {
        return token;
    }

    public String getTipus() {
        return tipus;
    }

    public long getExpiraEnSegons() {
        return expiraEnSegons;
    }

    public UserResponse getUsuari() {
        return usuari;
    }
}