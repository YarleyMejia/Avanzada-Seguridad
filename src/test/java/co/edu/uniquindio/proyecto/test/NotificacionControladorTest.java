package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.controladores.NotificacionControlador;
import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.modelo.documentos.Notificacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.NotificacionServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class NotificacionControladorTest {

    @InjectMocks
    private NotificacionControlador controlador;

    @Mock
    private NotificacionServicio servicio;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controlador).build();

    }
    @Test
        void crearNotificacion_DeberiaRetornarOk() throws Exception {
            NotificacionDTO dto = new NotificacionDTO("Nuevo reporte", "reporte", new ObjectId().toHexString(), new ObjectId().toHexString());

        mockMvc.perform(post("/api/notificaciones")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(dto))
                )
                .andExpect(status().isOk());

            verify(servicio, times(1)).notificar(any(NotificacionDTO.class));
        }

    @Test
    void crearNotificacion_DatosInvalidos_DeberiaLanzarError400() throws Exception {
        NotificacionDTO dto = new NotificacionDTO("", "", "", ""); // Campos vacíos

        mockMvc.perform(post("/api/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void listarPorUsuario_DeberiaRetornarLista() throws Exception {
        ObjectId userId = new ObjectId();
        List<Notificacion> notificaciones = List.of(
                crearNoti(userId, "Mensaje 1"),
                crearNoti(userId, "Mensaje 2")
        );

        when(servicio.listarPorUsuario(userId.toHexString())).thenReturn(notificaciones);

        mockMvc.perform(get("/api/notificaciones/usuario/" + userId.toHexString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].mensaje").value("Mensaje 1"))
                .andExpect(jsonPath("$[1].mensaje").value("Mensaje 2"));

        verify(servicio).listarPorUsuario(userId.toHexString());
    }

    private Notificacion crearNoti(ObjectId usuarioId, String mensaje) {
        Notificacion n = new Notificacion();
        n.setIdUsuario(usuarioId);
        n.setMensaje(mensaje);
        n.setFecha(LocalDateTime.now());
        n.setLeida(false);
        n.setTipo("reporte");
        n.setReporteId(new ObjectId());
        return n;
    }
    @Test
    void marcarComoLeida_DeberiaLlamarAlServicio() throws Exception {
        String notiId = new ObjectId().toHexString();

        mockMvc.perform(put("/api/notificaciones/" + notiId + "/leida"))
                .andExpect(status().isOk());

        verify(servicio, times(1)).marcarComoLeida(notiId);
    }
    }