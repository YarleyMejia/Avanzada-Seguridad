package co.edu.uniquindio.proyecto.servicios.interfaces;


import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EstadoReporteDTO;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public interface ReporteServicio {
    void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception;
    void editarReporte(String id, EditarReporteDTO reporte) throws Exception;
    void eliminarReporte(String id) throws Exception;
    void cambiarEstadoReporte(String id, EstadoReporte nuevoEstado) throws Exception;
    void marcarImportante(String id) throws Exception;
    void agregarComentario(CrearComentarioDTO crearComentarioDTO) throws Exception;
    List<ComentarioDTO> listarComentarios(String id)throws Exception;
    List<ReporteDTO> listarReportesPorUsuario(String idUsuario) throws Exception;
    ReporteDTO obtenerReportePorId(String id) throws Exception;

    List<ReporteDTO> listarReportesPorUsuarioYEstado(String idUsuario, EstadoReporte estado) throws Exception;
}
