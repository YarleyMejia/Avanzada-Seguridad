package co.edu.uniquindio.proyecto.dto;


public record TokenDTO(
        String token,
        String refreshToken,
        String mensaje,  // Nuevo campo para el mensaje
        boolean error    // Campo para indicar si hay error
) {}