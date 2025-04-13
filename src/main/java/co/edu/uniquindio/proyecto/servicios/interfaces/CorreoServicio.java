package co.edu.uniquindio.proyecto.servicios.interfaces;

public interface CorreoServicio {

    /**
     * Envia un correo electrónico al destinatario especificado.
     * @param destinatario Correo del destinatario.
     * @param asunto Asunto del correo.
     * @param cuerpo Cuerpo del mensaje.
     * @throws Exception en caso de error al enviar.
     */
    void enviarCorreo(String destinatario, String asunto, String cuerpo) throws Exception;
}