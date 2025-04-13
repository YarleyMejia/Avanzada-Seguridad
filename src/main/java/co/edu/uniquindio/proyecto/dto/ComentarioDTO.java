package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioDTO {

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;

    public ComentarioDTO() {
    }

    public ComentarioDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

