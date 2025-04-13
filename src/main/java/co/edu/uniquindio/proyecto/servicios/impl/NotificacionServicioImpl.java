package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificacionServicioImpl implements NotificacionServicio {


    @Override
    public void notificar(NotificacionDTO dto) {

    }

    @Override
    public List<Notificacion> listarPorUsuario(String idUsuario) {
        return List.of();
    }

    @Override
    public void marcarComoLeida(String id) {

    }
}

