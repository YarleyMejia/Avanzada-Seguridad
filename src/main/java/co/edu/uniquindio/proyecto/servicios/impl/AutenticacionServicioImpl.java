package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.excepciones.ElementoNoEncontradoException;
import co.edu.uniquindio.proyecto.mapper.UsuarioMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    @Override
    public void login(LoginDTO loginDTO) throws Exception {
        Usuario usuario = usuarioRepo.findByEmail(loginDTO.email())
                .orElseThrow(() -> new Exception("El correo no está registrado"));

        if (!usuario.getPassword().equals(loginDTO.password())) {
            throw new Exception("La contraseña es incorrecta");
        }

        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            throw new Exception("La cuenta aún no está activada");
        }

        
    }

    @Override
    public void validarCodigo(ValidarCodigoDTO validacion) {
        // Aquí iría la lógica si decides mantenerla aquí
    }

    @Override
    public void reenviarCodigo(String email) {
        // Aquí iría la lógica si decides mantenerla aquí
    }
}



