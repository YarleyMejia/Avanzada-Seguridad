package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.impl.ComentarioServicioImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ComentarioControladorTest {
    @InjectMocks
    private ComentarioServicioImpl comentarioServicio;

    @Mock
    private ComentarioRepo comentarioRepo;

    // Para tests futuros
    @Mock
    private ReporteRepo reporteRepo;

    @Mock
    private UsuarioRepo usuarioRepo;
    @Test
    void agregarComentario_Exitoso() {
        // Arrange
        String clienteId = new ObjectId().toHexString();
        String reporteId = new ObjectId().toHexString();
        ComentarioDTO dto = new ComentarioDTO(reporteId, clienteId, "Buen aporte", null);

        Comentario comentarioGuardado = new Comentario();
        comentarioGuardado.setId(new ObjectId());
        comentarioGuardado.setClienteId(new ObjectId(clienteId));
        comentarioGuardado.setReporteId(new ObjectId(reporteId));
        comentarioGuardado.setMensaje("Buen aporte");
        comentarioGuardado.setFecha(java.time.LocalDateTime.now());

        when(comentarioRepo.save(any(Comentario.class))).thenReturn(comentarioGuardado);

        // Act
        Comentario resultado = comentarioServicio.agregarComentario(dto);

        // Assert
        assertEquals("Buen aporte", resultado.getMensaje());
        assertEquals(new ObjectId(reporteId), resultado.getReporteId());
        assertEquals(new ObjectId(clienteId), resultado.getClienteId());
        assertNotNull(resultado.getFecha());
    }

    @Test
    void agregarComentario_ErrorAlGuardar_LanzaExcepcion() {
        // Arrange
        ComentarioDTO dto = new ComentarioDTO(new ObjectId().toHexString(), new ObjectId().toHexString(), "Error test", null);

        when(comentarioRepo.save(any(Comentario.class)))
                .thenThrow(new RuntimeException("Error al guardar comentario"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> comentarioServicio.agregarComentario(dto));
        assertEquals("Error al guardar comentario", ex.getMessage());
    }

    @Test
    void listarPorReporte_Exitoso() {
        // Arrange
        ObjectId reporteId = new ObjectId();

        Comentario comentario1 = new Comentario();
        comentario1.setId(new ObjectId());
        comentario1.setReporteId(reporteId);
        comentario1.setClienteId(new ObjectId());
        comentario1.setMensaje("Comentario 1");
        comentario1.setFecha(java.time.LocalDateTime.now());

        Comentario comentario2 = new Comentario();
        comentario2.setId(new ObjectId());
        comentario2.setReporteId(reporteId);
        comentario2.setClienteId(new ObjectId());
        comentario2.setMensaje("Comentario 2");
        comentario2.setFecha(java.time.LocalDateTime.now());

        when(comentarioRepo.findByReporteId(reporteId))
                .thenReturn(List.of(comentario1, comentario2));

        // Act
        List<Comentario> resultado = comentarioServicio.listarPorReporte(reporteId);

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Comentario 1", resultado.get(0).getMensaje());
        assertEquals("Comentario 2", resultado.get(1).getMensaje());
    }

    @Test
    void listarPorReporte_ErrorRepositorio_LanzaExcepcion() {
        // Arrange
        ObjectId reporteId = new ObjectId();

        when(comentarioRepo.findByReporteId(reporteId))
                .thenThrow(new RuntimeException("Error en base de datos"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> comentarioServicio.listarPorReporte(reporteId));
        assertEquals("Error en base de datos", ex.getMessage());
    }
}
