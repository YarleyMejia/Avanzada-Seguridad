package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificacionDTO(
<<<<<<< HEAD
        String mensaje,
        String tipo,
        String reporteId,
        String idUsuario
) {
}

=======
        @NotBlank String idUsuario,
        @NotBlank String mensaje
) {}
>>>>>>> pablo
