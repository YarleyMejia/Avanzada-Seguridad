package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

    private final ReporteRepo reporteRepo;
    private final ReporteMapper reporteMapper;

    @Override
    public void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception {

        // Convertir DTO a entidad
        Reporte reporte = reporteMapper.toDocument(crearReporteDTO);

        // Establecer la fecha actual
        reporte.setFecha(LocalDateTime.now());

        // Estado inicial del reporte
        reporte.setEstadoActual(EstadoReporte.PENDIENTE);

        // Inicializar contador de importancia
        reporte.setContadorImportante(0);

        // Historial de creación del reporte
        List<HistorialReporte> historial = new ArrayList<>();
        historial.add(
                new HistorialReporte(LocalDateTime.now(), EstadoReporte.PENDIENTE, "Reporte creado")
        );
        reporte.setHistorial(historial);

        // Guardar el reporte
        reporteRepo.save(reporte);
    }
}

