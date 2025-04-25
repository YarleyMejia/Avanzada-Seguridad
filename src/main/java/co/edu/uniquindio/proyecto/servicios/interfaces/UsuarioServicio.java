package co.edu.uniquindio.proyecto.servicios.interfaces;
import co.edu.uniquindio.proyecto.dto.RecuperarPasswordDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.ActivarCuentaDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CambiarPasswordDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EnviarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;

import java.util.List;

public interface UsuarioServicio {

    void crear(CrearUsuarioDTO cuenta) throws Exception;

    void eliminar(String id) throws Exception;

    void editarCuenta(String id,EditarUsuarioDTO cuenta) throws Exception;

    UsuarioDTO obtener(String id) throws Exception;

    //List<UsuarioDTO> listarTodos(String nombre, String ciudad, int pagina);
    List<UsuarioDTO> listarTodos(String nombre, int pagina);

    void enviarCodigoVerificacion(EnviarCodigoDTO enviarCodigoDTO) throws Exception;

    void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    void recuperarPassword(RecuperarPasswordDTO recuperarPasswordDTO) throws Exception;

    void activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception;
}