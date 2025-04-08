package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;

public interface AutenticacionServicio {

  //  UsuarioDTO login(LoginDTO loginDTO) throws Exception;

    void login(LoginDTO loginDTO) throws Exception;

    void validarCodigo(ValidarCodigoDTO validacion);

    void reenviarCodigo(String email);
}