package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ComentarioDTO(
        String reporteId,
        String clienteId,
        @NotBlank String mensaje,
        LocalDateTime fecha)
{}

