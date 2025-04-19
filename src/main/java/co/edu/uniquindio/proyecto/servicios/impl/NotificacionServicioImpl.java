package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.repositorios.NotificacionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
<<<<<<< HEAD
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
=======
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
>>>>>>> pablo
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServicioImpl implements NotificacionServicio {
    @Autowired
    private NotificacionRepo notificacionRepo;

<<<<<<< HEAD
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JavaMailSender mailSender;
=======
    private final NotificacionRepo notificacionRepo;
    private final SimpMessagingTemplate messagingTemplate;
>>>>>>> pablo

    @Override
    public void notificar(NotificacionDTO dto) {
        Notificacion noti = new Notificacion();
        noti.setMensaje(dto.mensaje());
        noti.setTipo(dto.tipo());
        noti.setReporteId(new ObjectId(dto.reporteId()));
        noti.setIdUsuario(new ObjectId(dto.idUsuario()));
        noti.setFecha(LocalDateTime.now());
        noti.setLeida(false);

<<<<<<< HEAD
        notificacionRepo.save(noti);

        // Enviar por WebSocket
        messagingTemplate.convertAndSend("/topic/notificaciones/" + dto.idUsuario(), dto);

        // Enviar por email (simplificado)
        enviarCorreo(dto.idUsuario(), dto.tipo(), dto.mensaje());
=======
        // Crear notificación con datos básicos
        Notificacion notificacion = Notificacion.builder()
                .mensaje(dto.mensaje())
                .fecha(LocalDateTime.now())
                .tipo("GENERAL")  // Puedes parametrizar esto si lo incluyes en el DTO
                .leida(false)
                .reporteId(null)
                .idUsuario(new ObjectId(dto.idUsuario()))
                .build();

        // Guardar en la base de datos
        notificacionRepo.save(notificacion);

        // Enviar al canal WebSocket personalizado del usuario
        String destino = "/topic/notificaciones/" + dto.idUsuario();
        messagingTemplate.convertAndSend(destino, dto.mensaje());
>>>>>>> pablo
    }

    @Override
    public List<Notificacion> listarPorUsuario(String idUsuario) {
<<<<<<< HEAD
        return notificacionRepo.findByIdUsuarioOrderByFechaDesc(new ObjectId(idUsuario));
    }

    @Override
    public void marcarComoLeida(String id) {
        Notificacion noti = notificacionRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        noti.setLeida(true);
        notificacionRepo.save(noti);
=======
        return notificacionRepo.findAllByIdUsuario(new ObjectId(idUsuario));
    }

    @Override
    public void marcarComoLeida(String idNotificacion) {
        Notificacion notificacion = notificacionRepo.findById(new ObjectId(idNotificacion))
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setLeida(true);
        notificacionRepo.save(notificacion);
>>>>>>> pablo
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
