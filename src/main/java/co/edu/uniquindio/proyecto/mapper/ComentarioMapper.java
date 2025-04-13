package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import org.bson.types.ObjectId;

public interface ComentarioMapper {
    public static Comentario toEntity(ComentarioDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setClienteId(new ObjectId(dto.clienteId()));
        comentario.setReporteId(new ObjectId(dto.reporteId()));
        comentario.setMensaje(dto.mensaje());
        comentario.setFecha(dto.fecha() != null ? dto.fecha() : java.time.LocalDateTime.now());
        return comentario;
    }

    public static ComentarioDTO toDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getReporteId().toHexString(),
                comentario.getClienteId().toHexString(),
                comentario.getMensaje(),
                comentario.getFecha()
        );
    }
}
