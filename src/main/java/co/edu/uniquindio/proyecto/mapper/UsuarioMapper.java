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

    UsuarioDTO toDTO(Usuario usuario);

    void toDocument(EditarUsuarioDTO editarUsuarioDTO, @MappingTarget Usuario usuario);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    // Metodo para mapear de ObjectId a String
    default ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null;
    }

    // Mapeo de CodigoValidacion a String
    default String map(CodigoValidacion value) {return value != null ? value.getCodigo() : null;}

}