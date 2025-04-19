package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
<<<<<<< HEAD

import java.util.List;

public interface NotificacionRepo extends MongoRepository<Notificacion, ObjectId> {
        List<Notificacion> findByIdUsuarioOrderByFechaDesc(ObjectId idUsuario);
=======
import java.util.List;

public interface NotificacionRepo extends MongoRepository<Notificacion, ObjectId> {

    List<Notificacion> findAllByIdUsuario(ObjectId idUsuario);

>>>>>>> pablo
}

