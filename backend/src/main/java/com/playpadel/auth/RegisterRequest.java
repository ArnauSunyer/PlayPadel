package com.playpadel.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "El nom és obligatori")
        @Size(max = 80)
        String nom,

        @NotBlank(message = "Els cognoms són obligatoris")
        @Size(max = 120)
        String cognoms,

        @NotBlank(message = "L'email és obligatori")
        @Email(message = "L'email no és vàlid")
        @Size(max = 254)
        String email,

        @Pattern(regexp = "^$|^\\+?[0-9 ]{9,19}$", message = "El telèfon no és vàlid")
        String telefon,

        @NotBlank(message = "La contrasenya és obligatòria")
        @Size(min = 8, max = 72, message = "La contrasenya ha de tenir entre 8 i 72 caràcters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                 message = "La contrasenya ha de tenir majúscula, minúscula i número")
        String password,

        @AssertTrue(message = "Cal acceptar la política de privacitat")
        boolean politicaAcceptada,

        boolean acceptaComunicacions
) {
}