package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.servicios.interfaces.CorreoServicio;
import org.springframework.stereotype.Service;

@Service
public class CorreoServicioImpl implements CorreoServicio {
    @Override
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        System.out.println("=== Simulación de envío de correo ===");
        System.out.println("Para: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println("Contenido:");
        System.out.println(cuerpo);
        System.out.println("======================================");
    }
}