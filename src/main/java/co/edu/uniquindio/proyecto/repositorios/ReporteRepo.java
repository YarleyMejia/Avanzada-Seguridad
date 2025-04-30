package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, ObjectId> {

    List<Reporte> findAllByUsuarioId(ObjectId usuarioId);

    List<Reporte> findAllByUsuarioIdAndEstadoActual(ObjectId usuarioId, EstadoReporte estadoActual);
    // Aquí puedes agregar métodos personalizados si los necesitas más adelante







}
