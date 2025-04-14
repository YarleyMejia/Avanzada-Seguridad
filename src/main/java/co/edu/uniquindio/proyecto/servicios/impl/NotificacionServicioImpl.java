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

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.repositorios.NotificacionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServicioImpl implements NotificacionServicio {
    @Autowired
    private NotificacionRepo notificacionRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void notificar(NotificacionDTO dto) {
        Notificacion noti = new Notificacion();
        noti.setMensaje(dto.mensaje());
        noti.setTipo(dto.tipo());
        noti.setReporteId(new ObjectId(dto.reporteId()));
        noti.setIdUsuario(new ObjectId(dto.idUsuario()));
        noti.setFecha(LocalDateTime.now());
        noti.setLeida(false);

        notificacionRepo.save(noti);

        // Enviar por WebSocket
        messagingTemplate.convertAndSend("/topic/notificaciones/" + dto.idUsuario(), dto);

        // Enviar por email (simplificado)
        enviarCorreo(dto.idUsuario(), dto.tipo(), dto.mensaje());

    }

    @Override
    public List<Notificacion> listarPorUsuario(String idUsuario) {

        return List.of();
        return notificacionRepo.findByIdUsuarioOrderByFechaDesc(new ObjectId(idUsuario));

    }

    @Override
    public void marcarComoLeida(String id) {


    }
}

        Notificacion noti = notificacionRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        noti.setLeida(true);
        notificacionRepo.save(noti);
    }

    private void enviarCorreo(String usuarioId, String asunto, String mensaje) {
        // Simulación. Deberías buscar el email real del usuario por su ID
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setTo("usuario@ejemplo.com"); // Reemplazar
        correo.setSubject("Notificación: " + asunto);
        correo.setText(mensaje);
        mailSender.send(correo);
    }

}

