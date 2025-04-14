package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public record EditarCategoriaDTO(
        @NotBlank String descripcion,
        @NotBlank String nombre,
        @NotBlank String id
) {
}

