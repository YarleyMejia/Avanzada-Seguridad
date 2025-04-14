package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.repositorios.NotificacionRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServicioImpl implements NotificacionServicio {

    private final NotificacionRepo notificacionRepo;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notificar(NotificacionDTO dto) {

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
    }

    @Override
    public List<Notificacion> listarPorUsuario(String idUsuario) {
        return notificacionRepo.findAllByIdUsuario(new ObjectId(idUsuario));
    }

    @Override
    public void marcarComoLeida(String idNotificacion) {
        Notificacion notificacion = notificacionRepo.findById(new ObjectId(idNotificacion))
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setLeida(true);
        notificacionRepo.save(notificacion);
    }
}

