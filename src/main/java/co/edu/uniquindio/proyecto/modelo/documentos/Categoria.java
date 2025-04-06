package co.edu.uniquindio.proyecto.modelo.documentos;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("categorias")

public class Categoria {
    @Id
    private ObjectId id;
    private String nombre;
    private String icono; // sino se va a usar, eliminarlo.

    @Builder

    public Categoria(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }
}