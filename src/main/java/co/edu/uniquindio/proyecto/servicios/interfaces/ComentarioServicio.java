package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import org.bson.types.ObjectId;

import java.util.List;

public interface ComentarioServicio {

    Comentario agregarComentario(ComentarioDTO comentarioDTO);
    List<Comentario> listarPorReporte(ObjectId reporteId);

}
