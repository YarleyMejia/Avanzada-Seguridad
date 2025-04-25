package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperarPasswordDTO(

        @NotBlank(message = "El código de verificación no puede estar vacío")
        String codigoValidacion,

        @NotBlank(message = "La nueva contraseña no puede estar vacía")
        @Length(min = 7, message = "La nueva contraseña debe tener al menos 7 caracteres")
        String nuevaPassword,

        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El formato del correo es inválido")
        String email

) {}