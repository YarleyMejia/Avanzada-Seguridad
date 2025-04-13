package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface ComentarioRepo extends MongoRepository<Comentario, String> {

        // Método para obtener comentarios por reporteId
        List<Comentario> findByReporteId(String reporteId);

        // Método para obtener comentarios por clientId
        List<Comentario> findByClienteId(String clientId);

        // Método para obtener comentarios de un cliente en un reporte específico
        List<Comentario> findByReporteIdAndClienteId(String reporteId, String clientId);

        // Método para obtener comentarios ordenados por fecha descendente
        List<Comentario> findByReporteIdOrderByFechaDesc(String reporteId);

        List<Comentario> findComentarioByTexto(String texto);
    }

