package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificacionDTO(
        @NotBlank String idUsuario,
        @NotBlank String mensaje
) {}