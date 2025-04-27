package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CrearComentarioDTO(
        @NotBlank(message = "El mensaje no puede estar vacío")
        @Size(max = 500, message = "El comentario no puede exceder 500 caracteres")
        String mensaje,

        @NotBlank(message = "El ID de usuario es obligatorio")
        String idUsuario,

        @NotBlank(message = "El ID de reporte es obligatorio")
        String idReporte
) {}