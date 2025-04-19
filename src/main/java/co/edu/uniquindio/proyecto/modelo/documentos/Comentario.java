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
@Document("comentarios")
public class Comentario {
    @Id
    private ObjectId id;
    private ObjectId reporteId;
    private ObjectId clienteId;
    private String mensaje;
    private LocalDateTime fecha;


    public Comentario(String mensaje, LocalDateTime fecha, ObjectId clienteId) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.clienteId = clienteId;
    }
}