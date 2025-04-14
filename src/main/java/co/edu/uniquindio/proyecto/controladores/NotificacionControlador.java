package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionControlador {

    private final NotificacionServicio notificacionServicio;

    // Endpoint que crea y envía la notificación por WebSocket
    @PostMapping("/enviar")
    public ResponseEntity<MensajeDTO<String>> enviarNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        try {
            notificacionServicio.notificar(dto); // Aquí dentro se dispara el WebSocket
            return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Notificación enviada vía WebSocket"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, "Error al enviar la notificación: " + e.getMessage()));
        }
    }

    // Listar notificaciones históricas por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<MensajeDTO<List<Notificacion>>> listarPorUsuario(@PathVariable String usuarioId) {
        try {
            List<Notificacion> notificaciones = notificacionServicio.listarPorUsuario(usuarioId);
            return ResponseEntity.ok(new MensajeDTO<>(false, notificaciones));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null));
        }
    }

    // Marcar notificación como leída
    @PutMapping("/leida/{id}")
    public ResponseEntity<MensajeDTO<String>> marcarComoLeida(@PathVariable String id) {
        try {
            notificacionServicio.marcarComoLeida(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación marcada como leída"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, "Error al marcar la notificación: " + e.getMessage()));
        }
    }
}