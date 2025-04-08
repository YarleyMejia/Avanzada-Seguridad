package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<String>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Autenticación exitosa. Ingreso al sistema."));
    }

    /*
    @PostMapping("/codigo")
    public ResponseEntity<String> validarCodigo(@Valid @RequestBody ValidarCodigoDTO validacion) throws Exception {
        autenticacionServicio.validarCodigo(validacion);
        return ResponseEntity.ok("Cuenta activada correctamente");
    }

    @PostMapping("/codigo/reenviar")
    public ResponseEntity<String> reenviarCodigo(@RequestParam String email) throws Exception {
        autenticacionServicio.reenviarCodigo(email);
        return ResponseEntity.ok("Se ha enviado un nuevo código de activación a tu correo");
    }

     */
}
