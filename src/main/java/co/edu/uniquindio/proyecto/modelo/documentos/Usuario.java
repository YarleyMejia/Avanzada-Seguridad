package co.edu.uniquindio.proyecto.modelo.documentos;


import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.enums.Rol;
import co.edu.uniquindio.proyecto.modelo.vo.CodigoValidacion;
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
@Document("usuarios")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;

    private String nombre;
    private Ciudad ciudad;
    private String direccion;
    private String email;
    private String telefono;
    private String password;
    private Rol rol;
    private EstadoUsuario estado;
    private LocalDateTime fechaRegistro;
    private CodigoValidacion codigoValidacion;

    @Builder
    public Usuario(String nombre, Ciudad ciudad, String direccion, String email, String telefono,
                   String password, Rol rol, EstadoUsuario estado, LocalDateTime fechaRegistro, CodigoValidacion codigoValidacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.codigoValidacion = codigoValidacion;
    }
}