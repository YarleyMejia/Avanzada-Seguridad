package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.mapper.CategoriaMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoriaServicioImpl implements CategoriaServicio {
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public Categoria crear(CrearCategoriaDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria editar(EditarCategoriaDTO dto) {
        Optional<Categoria> optional = categoriaRepo.findById(new ObjectId(dto.id()));
        if (optional.isPresent()) {
            Categoria existente = optional.get();
            existente.setNombre(dto.nombre());
            existente.setDescripcion(dto.descripcion());
            return categoriaRepo.save(existente);
        }
        throw new RuntimeException("Categoría no encontrada");
    }

    @Override
    public void eliminar(EliminarCategoriaDTO dto) {
        ObjectId id = new ObjectId(dto.id());
        if (!categoriaRepo.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada para eliminar");
        }
        categoriaRepo.deleteById(id);
    }

    @Override
    public Categoria buscarPorId(String id) {
        return categoriaRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepo.findAll();
    }

}