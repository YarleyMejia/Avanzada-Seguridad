package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteMapperTest {

    private final ReporteMapper reporteMapper = Mappers.getMapper(ReporteMapper.class);

    @Test
    public void testToDocument() {
        CrearReporteDTO dto = new CrearReporteDTO(
                "Fuga de agua",
                "Se rompió una tubería en la calle 20",
                List.of("https://imagen1.jpg", "https://imagen2.jpg"),
                "645a2f3f1a4b5c6d7e8f9a0b", // id de categoría
                new UbicacionDTO(4.535, -75.675),
                "645a2f3f1a4b5c6d7e8f9a01" // id de usuario
        );

        Reporte reporte = reporteMapper.toDocument(dto);

        assertNotNull(reporte);
        assertEquals(dto.titulo(), reporte.getTitulo());
        assertEquals(dto.descripcion(), reporte.getDescripcion());
        assertEquals(2, reporte.getFotos().size());
        assertEquals(0, reporte.getContadorImportante());
        assertNotNull(reporte.getFecha());
        assertEquals("PENDIENTE", reporte.getEstadoActual().name());
        assertNotNull(reporte.getClienteId());
        assertNotNull(reporte.getCategoriaId());
        assertNotNull(reporte.getUbicacion());
        assertEquals(dto.ubicacion().latitud(), reporte.getUbicacion().getLatitud());
    }
}
