package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.CorreoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final UsuarioRepo usuarioRepo;
    private final CorreoServicio correoServicio;

    // Mapa en memoria para almacenar temporalmente códigos de activación
    private final Map<String, String> codigosActivacion = new ConcurrentHashMap<>();

    @Override
    public void validarCodigo(ValidarCodigoDTO validacion) {
        Usuario usuario = usuarioRepo.findByEmail(validacion.email())
                .orElseThrow(() -> new RuntimeException("El correo no está registrado"));

        if (usuario.getEstado() == EstadoUsuario.ACTIVO) {
            throw new RuntimeException("La cuenta ya está activa");
        }

        String codigoEsperado = codigosActivacion.get(validacion.email());

        if (codigoEsperado == null) {
            throw new RuntimeException("No se ha solicitado un código de activación");
        }

        if (!codigoEsperado.equals(validacion.codigo())) {
            throw new RuntimeException("El código ingresado es incorrecto");
        }

        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuarioRepo.save(usuario);
        codigosActivacion.remove(validacion.email()); // Elimina el código después de activación
    }

    @Override
    public void reenviarCodigo(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("El correo no está registrado"));

        if (usuario.getEstado() == EstadoUsuario.ACTIVO) {
            throw new RuntimeException("La cuenta ya está activa");
        }

        String nuevoCodigo = generarCodigo();
        codigosActivacion.put(email, nuevoCodigo);

        correoServicio.enviarCorreo(email, "Código de activación", "Tu nuevo código de activación es: " + nuevoCodigo);
    }

    private String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}