package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    @Autowired
    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Comentario crearComentario(Comentario comentario) {
        return comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorReporte(String reporteId) {
        return comentarioRepo.findByReporteId(reporteId);
    }

    @Override
    public List<Comentario> obtenerComentariosPorCliente(String clientId) {
        return comentarioRepo.findByClientId(clientId);
    }
}
