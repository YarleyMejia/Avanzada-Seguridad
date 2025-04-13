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
    private String texto;
    private LocalDateTime fecha;
    private ObjectId clienteId;

    public Comentario(String texto, LocalDateTime fecha, ObjectId clienteId) {
        this.texto = texto;
        this.fecha = fecha;
        this.clienteId = clienteId;
    }
}