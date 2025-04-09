package co.edu.uniquindio.proyecto.servicios.interfaces;


import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;

public interface ReporteServicio {
    void crearReporte(CrearReporteDTO crearReporteDTO) throws Exception;
}