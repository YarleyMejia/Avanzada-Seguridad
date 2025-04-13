package co.edu.uniquindio.proyecto.servicios.interfaces;


import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EditarReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EstadoReporteDTO;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;


public interface ReporteServicio {
    void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception;
    void editarReporte(String id, EditarReporteDTO reporte) throws Exception;
    void eliminarReporte(String id) throws Exception;
    void cambiarEstadoReporte(String id, EstadoReporte nuevoEstado) throws Exception;



}

