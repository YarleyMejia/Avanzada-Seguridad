package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    @Override
    public Comentario crearComentario(String reporteId, String clienteId, ComentarioDTO dto) {
        Comentario comentario = new Comentario(dto.getMensaje(), LocalDateTime.now(), new ObjectId(clienteId));
        comentario.setReporteId(new ObjectId(reporteId));
        return comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorReporte(String reporteId) {
        return comentarioRepo.findByReporteId(reporteId);
    }

    @Override
    public List<Comentario> obtenerComentariosPorCliente(String clienteId) {
        return comentarioRepo.findByClienteId(clienteId);
    }

    @Override
    public Comentario actualizarComentario(String comentarioId, ComentarioDTO dto, String clienteId) {
        Comentario comentario = comentarioRepo.findById(String.valueOf(new ObjectId(comentarioId)))
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        if (!comentario.getClienteId().toHexString().equals(clienteId)) {
            throw new RuntimeException("No tiene permiso para actualizar este comentario");
        }

        comentario.setTexto(dto.getMensaje());
        comentario.setFecha(LocalDateTime.now());

        return comentarioRepo.save(comentario);
    }

    public List<Comentario> buscarComentariosPorTexto(String texto) {
        // Lógica para buscar comentarios por texto
        return comentarioRepo.findComentarioByTexto(texto);
    }

    @Override
    public Comentario eliminarComentario(String comentarioId, String clienteId) {
        return null;
    }

    @Override
    public Comentario buscarComentarioPorTexto(String texto) {
        return null;
    }
}
