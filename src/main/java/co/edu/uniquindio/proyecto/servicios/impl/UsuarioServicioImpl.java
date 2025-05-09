package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.RecuperarPasswordDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.ActivarCuentaDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CambiarPasswordDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EnviarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.paqueteUsuariosDTO.UsuarioDTO;
import co.edu.uniquindio.proyecto.excepciones.ElementoNoEncontradoException;
import co.edu.uniquindio.proyecto.excepciones.ElementoRepetidoException;
import co.edu.uniquindio.proyecto.mapper.UsuarioMapper;
import co.edu.uniquindio.proyecto.modelo.documentos.Usuario;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final MongoTemplate mongoTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void crear(CrearUsuarioDTO crearUsuarioDTO) throws Exception {

        // Validar que el email no esté repetido
        if(existeEmail(crearUsuarioDTO.email())){
            throw new ElementoRepetidoException("El email ya está registrado");
        }
        Usuario usuario = usuarioMapper.toDocument(crearUsuarioDTO);
        // Se codifica la contraseña
        usuario.setPassword(passwordEncoder.encode(crearUsuarioDTO.password()));
        usuarioRepo.save(usuario);

        // Enviar código de verificación después de guardar el usuario
        enviarCodigoVerificacion(new EnviarCodigoDTO(crearUsuarioDTO.email()));

    }

    private boolean existeEmail(String email){
        return usuarioRepo.findByEmail(email).isPresent();
    }


    @Override
    public void eliminar(String id) throws ElementoNoEncontradoException {

        // Obtener el usuario que se quiere eliminar
        Usuario usuario = obtenerPorId(id);
        // Modificar el estado del usuario
        usuario.setEstado(EstadoUsuario.ELIMINADO);
        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        usuarioRepo.save(usuario);
    }

    @Override
    public void editarCuenta(EditarUsuarioDTO editarUsuarioDTO) throws ElementoNoEncontradoException {
        // Obtener el usuario que se quiere modificar
        Usuario cuentaModificada = obtenerPorId(editarUsuarioDTO.id());
        // Mapear los datos actualizados al usuario existente
        usuarioMapper.toDocument(editarUsuarioDTO, cuentaModificada);
        //Como el objeto cuenta ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        usuarioRepo.save(cuentaModificada);
    }

    @Override
    public UsuarioDTO obtener(String id) throws ElementoNoEncontradoException {
        //Buscamos el usuario que se quiere obtener
        Usuario usuario = obtenerPorId(id);
        //Retornamos el usuario encontrado convertido a DTO
        return usuarioMapper.toDTO(usuario);
    }

    //Metodo usado en activarCuenta y cambiarPassword para obtener el usuario por email
    private Usuario obtenerPorEmail(String email) throws ElementoNoEncontradoException {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new ElementoNoEncontradoException("No se encontró el usuario con el email " + email);
        }
        return usuarioOptional.get();
    }

    // public List<UsuarioDTO> listarTodos(String nombre, String ciudad, int pagina) {
    @Override
    public List<UsuarioDTO> listarTodos(String nombre, int pagina) {

        // Validar que la pagina no sea menor a 0
        if(pagina < 0) throw new RuntimeException("La pagina no puede ser menor a 0");

        // Crear criterios dinámicos
        Criteria criteria = new Criteria();

        if (nombre != null && !nombre.isEmpty()) {
            criteria.and("nombre").regex(nombre, "i"); // Insensible a mayúsculas/minúsculas
        }

        /*if (ciudad != null && !ciudad.isEmpty()) {
            criteria.and("ciudad").regex(ciudad, "i");
        }*/

        // Crear la consulta con los criterios y la paginación de 5 elementos por página
        Query query = new Query(criteria).with(PageRequest.of(pagina, 5));

        List<Usuario> usuarios = mongoTemplate.find(query, Usuario.class);

        // Convertir la lista de usuarios a una lista de DTOs
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();

    }

    private Usuario obtenerPorId(String id) throws ElementoNoEncontradoException {
        // Buscamos el usuario que se quiere obtener
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        // Convertimos el id de String a ObjectId
        ObjectId objectId = new ObjectId(id);
        Optional<Usuario> optionalCuenta = usuarioRepo.findById(objectId);

        // Si no se encontró el usuario, lanzamos una excepción
        if(optionalCuenta.isEmpty()){
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        return optionalCuenta.get();
    }


    @Override
    public void enviarCodigoVerificacion(EnviarCodigoDTO enviarCodigoDTO) throws Exception {
        Usuario usuario = obtenerPorEmail(enviarCodigoDTO.email());
        String codigo = generarCodigo();
        usuario.setCodigoValidacion(new CodigoValidacion(

                LocalDateTime.now(),
                codigo
        ));
        usuarioRepo.save(usuario);

    }

    private String generarCodigo() {
        String digitos = "0123456789";
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int indice = (int) (Math.random() * digitos.length());
            codigo.append(digitos.charAt(indice));
        }
        return codigo.toString();
    }

    @Override
    public void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {

        Usuario usuario = obtenerPorEmail(cambiarPasswordDTO.email());

        if (usuario.getCodigoValidacion() == null) {
            throw new Exception("El usuario no cuenta con código de verificación");
        }

        if(!usuario.getCodigoValidacion().getCodigo().equals(cambiarPasswordDTO.codigoValidacion())) {
            throw new Exception("El código de verificación es incorrecto");
        }

        if(!LocalDateTime.now().isBefore(usuario.getCodigoValidacion().getFecha().plusMinutes(15))) {
            throw new Exception("El código de verificación ha caducado");
        }
        usuario.setPassword(cambiarPasswordDTO.nuevaPassword());
        usuario.setCodigoValidacion(null);
        usuarioRepo.save(usuario);
    }

    @Override
    public void activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception {
        Usuario usuario = obtenerPorEmail(activarCuentaDTO.email());

        if (usuario.getCodigoValidacion() == null) {
            throw new Exception("No se encontró el usuario con el email ");
        }

        if(!usuario.getCodigoValidacion().getCodigo().equals(activarCuentaDTO.codigoValidacion())) {
            throw new Exception("El código de verificación es incorrecto");
        }

        if(!LocalDateTime.now().isBefore(usuario.getCodigoValidacion().getFecha().plusMinutes(15))) {
            throw new Exception("El código de verificación ha caducado");
        }

        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setCodigoValidacion(null);
        usuarioRepo.save(usuario);
    }

    @Override
    public void recuperarPassword(RecuperarPasswordDTO RecuperarPasswordDTO)throws Exception {

        Usuario usuario = obtenerPorEmail(RecuperarPasswordDTO.email());

        if (usuario.getCodigoValidacion() == null) {
            throw new Exception("El usuario no cuenta con código de verificación");
        }

        if (!usuario.getCodigoValidacion().getCodigo().equals(RecuperarPasswordDTO.codigoValidacion())) {
            throw new Exception("El código de verificación es incorrecto");
        }



        usuario.setPassword(RecuperarPasswordDTO.nuevaPassword());
        usuario.setCodigoValidacion(null);
        usuarioRepo.save(usuario);


    }

}