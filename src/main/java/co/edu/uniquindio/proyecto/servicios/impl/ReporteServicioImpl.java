package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.paqueteReporteDTO.EditarReporteDTO;
import co.edu.uniquindio.proyecto.mapper.ComentarioMapper;
import co.edu.uniquindio.proyecto.mapper.ReporteMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.modelo.vo.Comentario;
import co.edu.uniquindio.proyecto.modelo.documentos.Reporte;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoReporte;
import co.edu.uniquindio.proyecto.modelo.vo.HistorialReporte;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReporteServicioImpl implements ReporteServicio {

    private final ComentarioRepo comentarioRepo;
    private final ComentarioMapper comentarioMapper;
    private final ReporteRepo reporteRepo;
    private final ReporteMapper reporteMapper;
    private final UsuarioRepo usuarioRepo;

    public ReporteServicioImpl(ComentarioRepo comentarioRepo,
                               ComentarioMapper comentarioMapper,
                               ReporteRepo reporteRepo,
                               ReporteMapper reporteMapper,
                               UsuarioRepo usuarioRepo) {
        this.comentarioRepo = comentarioRepo;
        this.comentarioMapper = comentarioMapper;
        this.reporteRepo = reporteRepo;
        this.reporteMapper = reporteMapper;
        this.usuarioRepo = usuarioRepo; // ✅ esto es lo que te está faltando
    }



    //private final ComentarioRepo comentarioRepo;
    //private final UsuarioServicio usuarioServicio;


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

    @Override
    public void cambiarEstadoReporte(String id, EstadoReporte nuevoEstado) throws Exception {
        ObjectId objectId;

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("ID de reporte inválido");
        }

        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new Exception("No se encontró un reporte con el ID proporcionado"));

        // Actualizar el estado actual
        reporte.setEstadoActual(nuevoEstado);

        // Registrar el cambio en el historial
        List<HistorialReporte> historial = reporte.getHistorial();
        if (historial == null) {
            historial = new ArrayList<>();
        }

        historial.add(new HistorialReporte(
                LocalDateTime.now(),
                nuevoEstado,
                "Cambio de estado manual"
        ));

        reporte.setHistorial(historial);

        // Guardar los cambios
        reporteRepo.save(reporte);
    }

    @Override
    public void marcarImportante(String id) throws Exception {
        ObjectId objectId;

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("ID de reporte inválido");
        }

        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new Exception("Reporte no encontrado"));

        // Aumentar el contador
        reporte.setContadorImportante(reporte.getContadorImportante() + 1);

        // Obtener el ID del usuario creador del reporte
        ObjectId creadorId = reporte.getUsuarioId();



// Buscar el usuario en la base de datos
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(creadorId);

        if (usuarioOptional.isPresent()) {
            Usuario creador = usuarioOptional.get();
            creador.setReputacion(creador.getReputacion() + 1);
            usuarioRepo.save(creador); // Guardar el cambio
        }


        // Guardar cambios
        reporteRepo.save(reporte);
    }

    @Override
    public void agregarComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {
        // Buscar el reporte al que se va a agregar el comentario
        Reporte reporte = reporteRepo.findById(new ObjectId(crearComentarioDTO.idReporte()))
                .orElseThrow(() -> new Exception("No se encontró el reporte con el ID: " + crearComentarioDTO.idReporte()));

        // Crear el comentario
        Comentario comentario = Comentario.builder()
                .reporteId(new ObjectId(crearComentarioDTO.idReporte()))
                .mensaje(crearComentarioDTO.mensaje())
                .fecha(LocalDateTime.now())
                .usuarioId(new ObjectId(crearComentarioDTO.idUsuario()))
                .build();

        // Agregarlo a la lista de comentarios
        List<Comentario> comentarios = reporte.getComentarios();
        if (comentarios == null) {
            comentarios = new ArrayList<>();
        }
        comentarios.add(comentario);

        // Setear los comentarios actualizados
        reporte.setComentarios(comentarios);

        // Guardar el reporte actualizado
        reporteRepo.save(reporte);
    }


    @Override
    public List<ComentarioDTO> listarComentarios(String idReporte) throws Exception {
        // Obtener el reporte completo por ID
        Reporte reporte = reporteRepo.findById(new ObjectId(idReporte))
                .orElseThrow(() -> new Exception("Reporte no encontrado"));

        // Obtener los comentarios del reporte
        List<Comentario> comentarios = reporte.getComentarios();

        // Convertirlos a DTO
        List<ComentarioDTO> comentarioDTOs = new ArrayList<>();
        for (Comentario c : comentarios) {
            comentarioDTOs.add(comentarioMapper.aDTO(c));
        }

        return comentarioDTOs;
    }

    @Override
    public List<ReporteDTO> listarReportesPorUsuario(String idUsuario) throws Exception {
        // Validar que el usuario existe (opcional pero recomendado)
        if (!usuarioRepo.existsById(new ObjectId(idUsuario))) {
            throw new Exception("El usuario con ID " + idUsuario + " no existe.");
        }

        // Buscar todos los reportes que pertenecen al usuario
        List<Reporte> reportes = reporteRepo.findAllByUsuarioId(new ObjectId(idUsuario));

        // Convertir a DTOs
        List<ReporteDTO> reporteDTOs = new ArrayList<>();
        for (Reporte reporte : reportes) {
            reporteDTOs.add(reporteMapper.aDTO(reporte));
        }

        return reporteDTOs;
    }


    @Override
    public ReporteDTO obtenerReportePorId(String id) throws Exception {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new Exception("ID de reporte inválido");
        }

        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new Exception("No se encontró un reporte con el ID proporcionado"));

        return reporteMapper.aDTO(reporte); //
    }
    @Override
    public List<ReporteDTO> listarReportesPorUsuarioYEstado(String idUsuario, EstadoReporte estado) throws Exception {
        ObjectId usuarioObjectId = new ObjectId(idUsuario);

        List<Reporte> reportes = reporteRepo.findAllByUsuarioIdAndEstadoActual(usuarioObjectId, estado);

        return reportes.stream()
                .map(reporteMapper::aDTO)
                .collect(Collectors.toList());
    }






}


