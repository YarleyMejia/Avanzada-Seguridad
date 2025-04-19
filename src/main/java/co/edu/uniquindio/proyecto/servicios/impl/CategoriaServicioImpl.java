package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    @Override
    public Categoria crear(CrearCategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .build();

        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria editar(EditarCategoriaDTO dto) {
        Categoria categoria = categoriaRepo.findById(new ObjectId(dto.id()))
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));

        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());

        return categoriaRepo.save(categoria);
    }

    @Override
    public void eliminar(EliminarCategoriaDTO dto) {
        Categoria categoria = categoriaRepo.findById(new ObjectId(dto.id()))
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));

        categoriaRepo.delete(categoria);
    }

    @Override
    public Categoria buscarPorId(String id) {
        return categoriaRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepo.findAll();
    }
<<<<<<< HEAD

=======
>>>>>>> pablo
}
