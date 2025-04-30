package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record ReporteDTO(
        String id,
        String titulo,
        String descripcion,
        LocalDateTime fechaCreacion,
        String usuarioId
) {}