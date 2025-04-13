package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.servicios.interfaces.CorreoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class CorreoServicioImpl implements CorreoServicio {

    private final JavaMailSender mailSender;

    @Override
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(cuerpo, true); // true permite HTML

        mailSender.send(mensaje);
    }
}
