package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.RecuperarPasswordDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;



    // implementacion satisfactoria 08/04/2025

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearUsuarioDTO cuenta) throws Exception{
        usuarioServicio.crear(cuenta);
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Su registro ha sido exitoso, verificar tu correo y activa tu cuenta"));
    }

    // implementacion satisfactoria hay que restringir algunops campos
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtener(@PathVariable String id) throws Exception{
        UsuarioDTO info = usuarioServicio.obtener(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String id) throws Exception{
        usuarioServicio.eliminar(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }


    // Listar usuarios con filtros y paginación
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarTodos(
            @RequestParam(required = false) String nombre,
            //@RequestParam(required = false) String ciudad, //otro parametro de filtro
            @RequestParam(defaultValue = "0") int pagina
    ) {
        // List<UsuarioDTO> lista = usuarioServicio.listarTodos(nombre, ciudad, pagina); //
        List<UsuarioDTO> lista = usuarioServicio.listarTodos(nombre, pagina);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    //implementacion satisfactoria 08/04/2025
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> editarCuenta(@PathVariable String id , @Valid @RequestBody EditarUsuarioDTO cuenta) throws Exception{
        usuarioServicio.editarCuenta(id,cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    //se prueba el metodo falta implementar la logica de enviar al correo
    @PostMapping("/codigoVerificacion")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoVerificacion(@Valid @RequestBody EnviarCodigoDTO enviarCodigoDTO) throws Exception {
        usuarioServicio.enviarCodigoVerificacion(enviarCodigoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Codigo enviado correctamente."));
    }

    // implementacion satisfactoria 08/04/2025
    @PutMapping("/{email}/cambiarPassword")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody CambiarPasswordDTO cambiarPasswordDTO) throws Exception {
        usuarioServicio.cambiarPassword(cambiarPasswordDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Password cambiado correctamente."));
    }

    @PostMapping("/recuperar-password")
    public ResponseEntity<MensajeDTO<String>> recuperarPassword(@RequestBody RecuperarPasswordDTO recuperarPasswordDTO) throws Exception
    {
        usuarioServicio.recuperarPassword(recuperarPasswordDTO);
        //return ResponseEntity.status(200).body(new MensajeDTO<>(false, "Su registro ha sido exitoso, verificar tu correo y activa tu cuenta"));
        return ResponseEntity.ok(new MensajeDTO<>(false, "Password recuperado correctamente."));
    }


    // implementacion satisfactoria 08/04/2025
    @PutMapping("/{email}/activar")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO activarCuentaDTO) throws Exception {
        usuarioServicio.activarCuenta(activarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Activado correctamente."));
    }



}