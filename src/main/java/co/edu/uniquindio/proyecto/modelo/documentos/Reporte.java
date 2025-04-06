package co.edu.uniquindio.proyecto.modelo.documentos;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("reportes")
public class Reporte {
    @Id
    private ObjectId id;
    private LocalDateTime fecha;
    private String descripcion;
    private int contadorImportante;
    private ObjectId clienteId;
    private String titulo;
    private Ubicacion ubicacion;
    private List<HistorialReporte> historial;
    private ObjectId categoriaId;
    private List<String> fotos;
    private EstadoReporte estadoActual;

    @Builder
    public Reporte( LocalDateTime fecha, String descripcion, int contadorImportante, ObjectId clienteId, String titulo, Ubicacion ubicacion, List<HistorialReporte> historial, ObjectId categoriaId, List<String> fotos, EstadoReporte estadoActual) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.contadorImportante = contadorImportante;
        this.clienteId = clienteId;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.historial = historial;
        this.categoriaId = categoriaId;
        this.fotos = fotos;
        this.estadoActual = estadoActual;
    }
}