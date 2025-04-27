package co.edu.uniquindio.proyecto.modelo.vo;

import lombok.*;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {

    private ObjectId id;
    private ObjectId reporteId;
    private String mensaje;
    private LocalDateTime fecha;
    private ObjectId usuarioId;

}