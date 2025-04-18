package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.ValidarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.enums.Rol;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;
    private final UsuarioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO token = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));
    }

    @PostMapping("/crear-admin")
    public ResponseEntity<String> crearAdmin() {
        /*if (usuarioRepo.existsByEmail("admin@correo.com")) {
            return ResponseEntity.badRequest().body("El admin ya existe");
        }*/

        Usuario admin = new Usuario();
        admin.setEmail("admin@correo.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRol(Rol.ADMINISTRADOR);
        admin.setNombre("Administrador");
        admin.setEstado(EstadoUsuario.valueOf("ACTIVO"));

        usuarioRepo.save(admin);

        return ResponseEntity.ok("Admin creado correctamente");
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