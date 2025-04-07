package co.edu.uniquindio.proyecto.modelo.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("notificaciones")
public class Notificacion {
    @Id
    private ObjectId id;
    private String mensaje;
    private LocalDateTime fecha;
    private String tipo;
    private boolean leida;
    private ObjectId reporteId;
    private ObjectId idUsuario;

    @Builder
    public Notificacion(String mensaje, LocalDateTime fecha, String tipo, boolean leida, ObjectId reporteId, ObjectId idUsuario) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.tipo = tipo;
        this.leida = leida;
        this.reporteId = reporteId;
        this.idUsuario = idUsuario;
    }
}