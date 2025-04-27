package co.edu.uniquindio.proyecto.dto.paqueteReporteDTO;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;

public record EditarReporteDTO(
        @NotBlank String titulo,
        @NotBlank String descripcion,
        @NotNull Ubicacion ubicacion,
        @NotNull ObjectId categoriaId,
        //@NotNull String categoriaId,
        @NotNull EstadoReporte estadoActual,
        List<String> fotos
) {}