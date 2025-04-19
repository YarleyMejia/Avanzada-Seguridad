package co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO;

public record UsuarioDTO (
        String id,
        String nombre,
        String email,
        String telefono,
        String ciudad,
        String estado,
        String rol,
        String fechaRegistro,
        String codigoValidacion,
        int reputacion
){}