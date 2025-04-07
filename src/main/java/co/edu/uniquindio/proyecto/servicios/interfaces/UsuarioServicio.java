package co.edu.uniquindio.proyecto.servicios.interfaces;
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

    void editar(EditarUsuarioDTO cuenta) throws Exception;

    UsuarioDTO obtener(String id) throws Exception;

    List<UsuarioDTO> listarTodos(String nombre, String ciudad, int pagina);

    void enviarCodigoVerificacion(EnviarCodigoDTO enviarCodigoDTO);

    void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO);

    void activarCuenta(ActivarCuentaDTO activarCuentaDTO);
}