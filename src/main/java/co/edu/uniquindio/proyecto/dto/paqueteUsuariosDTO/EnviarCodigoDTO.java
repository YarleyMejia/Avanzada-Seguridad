package co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public record EnviarCodigoDTO(
        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El formato del correo es inválido")
        String email
) {}