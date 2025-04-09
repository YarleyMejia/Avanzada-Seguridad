package co.edu.uniquindio.proyecto.modelo.vo;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialReporte {

    private LocalDateTime fechaCambio;
    private EstadoReporte estado;
    private String descripcion;
    private String idModerador; // opcional, puede ser null si fue el usuario

    public HistorialReporte(LocalDateTime now, EstadoReporte estadoReporte, String reporteCreado) {
    }

}
