package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;

import java.util.List;

public interface NotificacionServicio {
    void notificar(NotificacionDTO dto);

    List<Notificacion> listarPorUsuario(String idUsuario);

    void marcarComoLeida(String id);
}

