package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @PostMapping("/{reporteId}")
    public Comentario crearComentario(@PathVariable String reporteId,
                                      @RequestParam String clienteId,
                                      @RequestBody @Valid ComentarioDTO dto) {
        return comentarioServicio.crearComentario(reporteId, clienteId, dto);
    }

    @GetMapping("/reporte/{reporteId}")
    public List<Comentario> obtenerPorReporte(@PathVariable String reporteId) {
        return comentarioServicio.obtenerComentariosPorReporte(reporteId);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Comentario> obtenerPorCliente(@PathVariable String clienteId) {
        return comentarioServicio.obtenerComentariosPorCliente(clienteId);
    }

    @PutMapping("/{comentarioId}")
    public Comentario actualizar(@PathVariable String comentarioId,
                                 @RequestParam String clienteId,
                                 @RequestBody @Valid ComentarioDTO dto) {
        return comentarioServicio.actualizarComentario(comentarioId, dto, clienteId);
    }

    @DeleteMapping("/{comentarioId}")
    public void eliminar(@PathVariable String comentarioId,
                         @RequestParam String clienteId) {
        comentarioServicio.eliminarComentario(comentarioId, clienteId);
    }

    @GetMapping("/buscar")
    public List<Comentario> buscarPorTexto(@RequestParam String texto) {
        return (List<Comentario>) comentarioServicio.buscarComentarioPorTexto(texto);
    }
}

