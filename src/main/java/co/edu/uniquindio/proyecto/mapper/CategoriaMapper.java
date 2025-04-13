package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import org.bson.types.ObjectId;

public interface CategoriaMapper {

    public static Categoria toEntity(CrearCategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        return categoria;
    }

    public static Categoria toEntity(EditarCategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(new ObjectId(dto.id()));
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        return categoria;
    }

}
