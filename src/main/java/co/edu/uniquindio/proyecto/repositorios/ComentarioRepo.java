package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;

import java.util.List;

public interface ComentarioRepo {

    @Repository
    public interface ComentarioRepo extends MongoRepository<Comentario, String> {

        // Método para obtener comentarios por reporteId
        List<Comentario> findByReporteId(String reporteId);

        // Método para obtener comentarios por clientId
        List<Comentario> findByClientId(String clientId);

        // Método para obtener comentarios de un cliente en un reporte específico
        List<Comentario> findByReporteIdAndClientId(String reporteId, String clientId);

        // Método para obtener comentarios ordenados por fecha descendente
        List<Comentario> findByReporteIdOrderByFechaDesc(String reporteId);
    }
}
