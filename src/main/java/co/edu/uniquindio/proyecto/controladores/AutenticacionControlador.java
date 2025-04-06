package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autenticacion")
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    // POST /api/autenticacion/codigo → Para validar el código
    @PostMapping("/codigo")
    public ResponseEntity<String> validarCodigo(@Valid @RequestBody ValidarCodigoDTO validacion) throws Exception {
        autenticacionServicio.validarCodigo(validacion);
        return ResponseEntity.ok("Cuenta activada correctamente");
    }

    // POST /api/autenticacion/codigo/reenviar → Reenviar el código a un email
    @PostMapping("/codigo/reenviar")
    public ResponseEntity<String> reenviarCodigo(@RequestParam String email) throws Exception {
        autenticacionServicio.reenviarCodigo(email);
        return ResponseEntity.ok("Se ha enviado un nuevo código de activación a tu correo");
    }
}