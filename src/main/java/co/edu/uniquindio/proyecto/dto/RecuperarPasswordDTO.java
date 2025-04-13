package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperarPasswordDTO(
        @NotBlank String codigoValidacion,
        @NotBlank @Length(min=7) String nuevaPassword,
        @NotBlank @Email String email
) {
    
}