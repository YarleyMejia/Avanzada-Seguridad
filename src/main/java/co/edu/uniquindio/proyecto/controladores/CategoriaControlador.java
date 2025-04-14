package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO<Categoria>> crear(@Valid @RequestBody CrearCategoriaDTO dto) {
        Categoria categoria = categoriaServicio.crear(dto);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, categoria));
    }

    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO<Categoria>> editar(@Valid @RequestBody EditarCategoriaDTO dto) {
        Categoria categoria = categoriaServicio.editar(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, categoria));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<String>> eliminar(@Valid @RequestBody EliminarCategoriaDTO dto) {
        categoriaServicio.eliminar(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoría eliminada correctamente"));
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<MensajeDTO<Categoria>> buscarPorId(@PathVariable String id) {
        Categoria categoria = categoriaServicio.buscarPorId(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, categoria));
    }

    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO<List<Categoria>>> listar() {
        List<Categoria> categorias = categoriaServicio.listar();
        return ResponseEntity.ok(new MensajeDTO<>(false, categorias));
    }
}
