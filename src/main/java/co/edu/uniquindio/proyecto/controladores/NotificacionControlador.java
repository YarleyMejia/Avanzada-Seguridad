package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {

    @Autowired
    private NotificacionServicio notificacionServicio;

    @PostMapping
    public void crearNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        notificacionServicio.notificar(dto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable String usuarioId) {
        return notificacionServicio.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}/leida")
    public void marcarComoLeida(@PathVariable String id) {
        notificacionServicio.marcarComoLeida(id);
    }
}
