package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EditarReporteDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReporteServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteControlador {

    private final ReporteServicio reporteServicio;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<String>> crearReporte(@Valid @RequestBody CrearReporteDTO crearReporteDTO) throws Exception {
        reporteServicio.crearReporte(crearReporteDTO);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Reporte creado exitosamente"));
    }

    // Editar un reporte existente
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/editar/{id}")
    public ResponseEntity<MensajeDTO<String>> editar(@PathVariable String id, @Valid @RequestBody EditarReporteDTO dto) throws Exception {
        reporteServicio.editarReporte(id, dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte editado correctamente"));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String id) {
        try {
            reporteServicio.eliminarReporte(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    //@SecurityRequirement(name = "bearerAuth")
   // @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<MensajeDTO<String>> cambiarEstado(
            @PathVariable String id,
            @RequestParam EstadoReporte nuevoEstado


    ) {
        try {
            reporteServicio.cambiarEstadoReporte(id, nuevoEstado);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Estado del reporte actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
        }
    }


    // Marca un reporte como importante
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/importante")
    public ResponseEntity<String> marcarImportante(@PathVariable String id) throws Exception {
        reporteServicio.marcarImportante(id);
        return ResponseEntity.ok("Reporte calificado como importante");
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/comentarios")
    public ResponseEntity<MensajeDTO<String>> agregarComentario(
            @Valid @RequestBody CrearComentarioDTO crearComentarioDTO
    ) {
        try {
            reporteServicio.agregarComentario(crearComentarioDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario agregado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/reporte/{id}/comentarios")
    public ResponseEntity<MensajeDTO> obtenerComentariosDeReporte(@PathVariable String id) throws Exception {

            List<ComentarioDTO> comentarios = reporteServicio.listarComentarios(id);
            return ResponseEntity.ok(new MensajeDTO(false, comentarios));

    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> listarReportesPorUsuario(@PathVariable String id) throws Exception {
        List<ReporteDTO> reportes = reporteServicio.listarReportesPorUsuario(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, reportes));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<ReporteDTO>> obtenerReportePorId(@PathVariable String id) throws Exception {

            ReporteDTO reporte = reporteServicio.obtenerReportePorId(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, reporte));

    }


    
    @GetMapping("/usuario/{id}/estado/{estado}")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> listarReportesPorUsuarioYEstado(
            @PathVariable String id,
            @PathVariable EstadoReporte estado
    ) throws Exception {
        List<ReporteDTO> reportes = reporteServicio.listarReportesPorUsuarioYEstado(id, estado);
        return ResponseEntity.ok(new MensajeDTO<>(false, reportes));
    }





}
