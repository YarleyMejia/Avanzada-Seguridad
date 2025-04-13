package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;

import java.util.List;

public interface ComentarioServicio {

    Comentario crearComentario(Comentario comentario);

    List<Comentario> obtenerComentariosPorReporte(String reporteId);

    List<Comentario> obtenerComentariosPorCliente(String clientId);
}
