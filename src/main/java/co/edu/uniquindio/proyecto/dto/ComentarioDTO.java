package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;



public record ComentarioDTO(
        String reporteId,
        @NotBlank String mensaje,
        String fecha,
        String usuarioId
        )
{}