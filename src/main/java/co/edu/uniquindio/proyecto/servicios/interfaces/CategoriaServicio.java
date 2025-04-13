package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;

import java.util.List;

public interface CategoriaServicio {
    Categoria crear(CrearCategoriaDTO dto);
    Categoria editar(EditarCategoriaDTO dto);
    void eliminar(EliminarCategoriaDTO dto);
    Categoria buscarPorId(String id);
    List<Categoria> listar();
}