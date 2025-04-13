package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;

import java.util.List;

public interface ComentarioServicio {

    Comentario crearComentario(String reporteId, String clienteId, ComentarioDTO dto);

    List<Comentario> obtenerComentariosPorReporte(String reporteId);

    List<Comentario> obtenerComentariosPorCliente(String clientId);

    Comentario actualizarComentario(String comentarioId, ComentarioDTO dto, String clienteId);

    Comentario eliminarComentario(String comentarioId, String clienteId);

    Comentario buscarComentarioPorTexto(String texto);
}
