package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControlador {

    private CategoriaServicio categoriaServicio;

    @PostMapping
    public Categoria crear(@Valid @RequestBody CrearCategoriaDTO dto) {
        return categoriaServicio.crear(dto);
    }

    @PutMapping
    public Categoria editar(@Valid @RequestBody EditarCategoriaDTO dto) {
        return categoriaServicio.editar(dto);
    }

    @DeleteMapping
    public void eliminar(@Valid @RequestBody EliminarCategoriaDTO dto) {
        categoriaServicio.eliminar(dto);
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable String id) {
        return categoriaServicio.buscarPorId(id);
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaServicio.listar();
    }
}

