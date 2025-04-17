package co.edu.uniquindio.proyecto.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*public record TokenDTO (
        String token,
        String refreshToken
){}*/

public record TokenDTO(
        String token,
        String refreshToken,
        String mensaje,  // Nuevo campo para el mensaje
        boolean error    // Campo para indicar si hay error
) {}