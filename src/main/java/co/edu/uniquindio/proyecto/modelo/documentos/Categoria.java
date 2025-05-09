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
    private String descripcion; // sino se va a usar, eliminarlo.

    @Builder

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}