package co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EnviarCodigoDTO(
        @NotBlank @Email String email
) {}