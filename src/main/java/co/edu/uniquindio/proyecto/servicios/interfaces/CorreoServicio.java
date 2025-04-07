package co.edu.uniquindio.proyecto.servicios.interfaces;

public interface CorreoServicio {
    void enviarCorreo(String destinatario, String asunto, String cuerpo);
}