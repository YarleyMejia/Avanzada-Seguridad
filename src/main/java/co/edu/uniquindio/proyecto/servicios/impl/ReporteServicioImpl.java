package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EditarReporteDTO;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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

    @Override
    public void editarReporte(String id, EditarReporteDTO dto) throws Exception {
        // Convertir el id String a ObjectId
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("ID de reporte inválido");
        }

        // Buscar el reporte existente
        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new Exception("No se encontró un reporte con el ID especificado"));

        // Actualizar campos
        reporte.setTitulo(dto.titulo());
        reporte.setDescripcion(dto.descripcion());
        reporte.setUbicacion(dto.ubicacion());
        reporte.setCategoriaId(dto.categoriaId());
        reporte.setEstadoActual(dto.estadoActual());
        reporte.setFotos(dto.fotos());

        // Agregar entrada al historial
        HistorialReporte cambio = new HistorialReporte(LocalDateTime.now(), dto.estadoActual(), "Reporte editado");
        List<HistorialReporte> historial = reporte.getHistorial();
        historial.add(cambio);
        reporte.setHistorial(historial);

        // Guardar los cambios
        reporteRepo.save(reporte);
    }

    @Override
    public void eliminarReporte(String id) throws Exception {
        ObjectId objectId;

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("ID de reporte inválido");
        }

        // Verificar si el reporte existe
        if (!reporteRepo.existsById(objectId)) {
            throw new Exception("No se encontró un reporte con el ID proporcionado");
        }

        // Eliminar el reporte
        reporteRepo.deleteById(objectId);
    }





}
