package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public record EliminarCategoriaDTO(
        @NotBlank String id
) {
}
