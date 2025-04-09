package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.modelo.vo.Ubicacion;
import org.bson.types.ObjectId;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "estadoActual", constant = "PENDIENTE")
    @Mapping(target = "contadorImportante", constant = "0")
    @Mapping(target = "clienteId", expression = "java(new org.bson.types.ObjectId(dto.idUsuario()))")
    @Mapping(target = "categoriaId", expression = "java(new org.bson.types.ObjectId(dto.categoriaId()))")
    @Mapping(target = "historial", expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "ubicacion", expression = "java(mapUbicacion(dto.ubicacion()))")
    @Mapping(target = "fotos", source = "rutaImagenes")
    Reporte toDocument(CrearReporteDTO dto);

    // Conversión manual de DTO a VO
    default Ubicacion mapUbicacion(UbicacionDTO dto) {
        return dto != null ? new Ubicacion(dto.latitud(), dto.longitud()) : null;
    }

    // MapStruct requiere estos para IDs en MongoDB
    default String map(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    default ObjectId map(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}