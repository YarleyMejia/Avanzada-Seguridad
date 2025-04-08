package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.ActivarCuentaDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CambiarPasswordDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EnviarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    // Registro de usuario nuevo usuario, se debe validar codigo de autenticacion
        @PostMapping
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearUsuarioDTO cuenta) throws Exception{
        usuarioServicio.crear(cuenta);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Su registro ha sido exitoso, verificar tu correo y activa tu cuenta"));
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtener(@PathVariable String id) throws Exception{
        UsuarioDTO info = usuarioServicio.obtener(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String id) throws Exception{
        usuarioServicio.eliminar(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }


    // Listar usuarios con filtros y paginación
    @GetMapping
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarTodos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ciudad,
            @RequestParam(defaultValue = "0") int pagina
    ) {
        List<UsuarioDTO> lista = usuarioServicio.listarTodos(nombre, ciudad, pagina);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO<String>> editarCuenta(@Valid @RequestBody EditarUsuarioDTO cuenta) throws Exception{
        usuarioServicio.editarCuenta(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }


    @PostMapping("/codigoVerificacion")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoVerificacion(@RequestBody EnviarCodigoDTO enviarCodigoDTO) throws Exception {
        usuarioServicio.enviarCodigoVerificacion(enviarCodigoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Codigo enviado correctamente."));
    }


    @PutMapping("/{email}/password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        usuarioServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Password cambiado correctamente."));
    }


    @PutMapping("/{email}/activar")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        usuarioServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Activado correctamente."));
    }



}