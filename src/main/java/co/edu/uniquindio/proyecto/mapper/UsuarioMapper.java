package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.vo.CodigoValidacion;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "rol", constant = "CLIENTE")
    @Mapping(target = "estado", constant = "INACTIVO")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Usuario toDocument(CrearUsuarioDTO usuarioDTO);

    // Mapeo completo de Usuario -> UsuarioDTO
    @Mapping(target = "id", expression = "java(map(usuario.getId()))")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "ciudad", expression = "java(usuario.getCiudad() != null ? usuario.getCiudad().name() : null)")
    @Mapping(target = "estado", expression = "java(usuario.getEstado() != null ? usuario.getEstado().name() : null)")
    @Mapping(target = "rol", expression = "java(usuario.getRol() != null ? usuario.getRol().name() : null)")
    @Mapping(target = "fechaRegistro", expression = "java(usuario.getFechaRegistro() != null ? usuario.getFechaRegistro().toString() : null)")
    @Mapping(target = "codigoValidacion", expression = "java(map(usuario.getCodigoValidacion()))")
    @Mapping(target = "reputacion", source = "reputacion")
    UsuarioDTO toDTO(Usuario usuario);

    // Método para actualizar los datos del usuario existente
    void toDocument(EditarUsuarioDTO editarUsuarioDTO, @MappingTarget Usuario usuario);

    // Método auxiliar para mapear ObjectId -> String
    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }

    // Método auxiliar para mapear String -> ObjectId
    default ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null;
    }

    // Método auxiliar para mapear CodigoValidacion -> String
    default String map(CodigoValidacion value) {
        return value != null ? value.getCodigo() : null;
    }
}