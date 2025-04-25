package co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambiarPasswordDTO(

        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El formato del correo es inválido")
        String email,

        @NotBlank(message = "La contraseña actual no puede estar vacía")
        String passwordActual,

        @NotBlank(message = "La nueva contraseña no puede estar vacía")
        @Length(min = 7, message = "La nueva contraseña debe tener al menos 7 caracteres")
        String nuevaPassword

) {}