package co.edu.uniquindio.proyecto.dto.paqueteReporteDTO;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import jakarta.validation.constraints.NotNull;


public record EstadoReporteDTO(

        @NotNull EstadoReporte estadoActual,
        @NotNull EstadoReporte nuevoEstado

) {}