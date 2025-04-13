package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.CrearCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EditarCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.EliminarCategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.impl.CategoriaServicioImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoriaControladorTest {

    @InjectMocks
    private CategoriaServicioImpl categoriaServicio;

    @Mock
    private CategoriaRepo categoriaRepo;
    @Test
    void editarCategoria_Exitoso() {
        // Arrange
        ObjectId id = new ObjectId();
        Categoria existente = new Categoria();
        existente.setId(id);
        existente.setNombre("Viejo Nombre");
        existente.setDescripcion("Vieja descripción");

        EditarCategoriaDTO dto = new EditarCategoriaDTO(id.toHexString(), "Nuevo Nombre", "Nueva descripción");

        when(categoriaRepo.findById(id)).thenReturn(Optional.of(existente));
        when(categoriaRepo.save(any(Categoria.class))).thenAnswer(invoc -> invoc.getArgument(0));

        // Act
        Categoria resultado = categoriaServicio.editar(dto);

        // Assert
        assertEquals("Nuevo Nombre", resultado.getNombre());
        assertEquals("Nueva descripción", resultado.getDescripcion());
        assertEquals(id, resultado.getId());
    }

    @Test
    void editarCategoria_NoEncontrada_LanzaExcepcion() {
        // Arrange
        String id = new ObjectId().toHexString();
        EditarCategoriaDTO dto = new EditarCategoriaDTO(id, "Nombre", "Descripción");

        when(categoriaRepo.findById(new ObjectId(id))).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> categoriaServicio.editar(dto));
        assertEquals("Categoría no encontrada", ex.getMessage());
    }

    @Test
    void crearCategoria_Exitoso() {
        // Arrange
        CrearCategoriaDTO dto = new CrearCategoriaDTO("Nueva categoría", "Descripción");

        Categoria categoriaGuardada = new Categoria();
        categoriaGuardada.setId(new ObjectId());
        categoriaGuardada.setNombre("Nueva categoría");
        categoriaGuardada.setDescripcion("Descripción");

        when(categoriaRepo.save(any(Categoria.class))).thenReturn(categoriaGuardada);

        // Act
        Categoria resultado = categoriaServicio.crear(dto);

        // Assert
        assertEquals("Nueva categoría", resultado.getNombre());
        assertEquals("Descripción", resultado.getDescripcion());
        assertNotNull(resultado.getId());
    }

    @Test
    void crearCategoria_ErrorEnRepositorio_LanzaExcepcion() {
        // Arrange
        CrearCategoriaDTO dto = new CrearCategoriaDTO("Categoría", "Descripción");

        when(categoriaRepo.save(any(Categoria.class)))
                .thenThrow(new RuntimeException("Error de base de datos"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> categoriaServicio.crear(dto));
        assertEquals("Error de base de datos", ex.getMessage());
    }

    @Test
    void eliminarCategoria_Exitoso() {
        // Arrange
        ObjectId id = new ObjectId();
        EliminarCategoriaDTO dto = new EliminarCategoriaDTO(id.toHexString());

        when(categoriaRepo.existsById(id)).thenReturn(true);
        doNothing().when(categoriaRepo).deleteById(id);

        // Act
        categoriaServicio.eliminar(dto);

        // Assert
        verify(categoriaRepo, times(1)).deleteById(id);
    }

    @Test
    void eliminarCategoria_NoExiste_LanzaExcepcion() {
        // Arrange
        String id = new ObjectId().toHexString();
        EliminarCategoriaDTO dto = new EliminarCategoriaDTO(id);

        when(categoriaRepo.existsById(new ObjectId(id))).thenReturn(false);

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> categoriaServicio.eliminar(dto));
        assertEquals("Categoría no encontrada para eliminar", ex.getMessage());
    }
}
