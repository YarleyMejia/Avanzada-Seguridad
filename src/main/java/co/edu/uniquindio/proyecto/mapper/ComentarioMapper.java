package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.vo.Comentario;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(source = "reporteId", target = "reporteId", qualifiedByName = "objectIdToString")
    @Mapping(source = "usuarioId", target = "usuarioId", qualifiedByName = "objectIdToString")
    @Mapping(source = "fecha", target = "fecha", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    ComentarioDTO aDTO(Comentario comentario);

    @Named("objectIdToString")
    static String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }


    //Si quieres un método de conversión inversa, agrégalo así:
    // @Mapping(source = "reporteId", target = "reporteId", qualifiedByName = "stringToObjectId")
    // Comentario deDTO(ComentarioDTO dto);

    // @Named("stringToObjectId")
    // static ObjectId stringToObjectId(String id) {
    //     return id != null ? new ObjectId(id) : null;
    // }
}