package co.edu.uniquindio.proyecto.dto.reporte;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditarReporteDTO {

    @NotNull
    private LocalDateTime fecha;

    @NotBlank
    private String descripcion;

    @NotNull
    private Integer contadorImportante;

    @NotBlank
    private String titulo;

    @NotNull
    private Ubicacion ubicacion;

    @NotNull
    private List<HistorialReporte> historial;

    @NotBlank
    private String categoriaId;

    private List<String> fotos;

    @NotNull
    private EstadoReporte estadoActual;

}