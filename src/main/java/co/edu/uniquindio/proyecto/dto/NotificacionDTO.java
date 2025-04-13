package co.edu.uniquindio.proyecto.dto;

public record NotificacionDTO(
        String mensaje,
        String tipo,
        String reporteId,
        String idUsuario
) {
}
