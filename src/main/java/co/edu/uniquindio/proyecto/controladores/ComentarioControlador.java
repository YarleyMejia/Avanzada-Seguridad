package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.documentos.Comentario;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;


    @GetMapping("/reporte/{reporteId}")
    public List<Comentario> listarPorReporte(@PathVariable String reporteId) {
        return comentarioServicio.listarPorReporte(new ObjectId(reporteId));
    }


}

