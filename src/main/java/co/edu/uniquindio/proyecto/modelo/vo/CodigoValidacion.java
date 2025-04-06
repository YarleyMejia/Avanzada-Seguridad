package co.edu.uniquindio.proyecto.modelo.vo;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoValidacion {
    private LocalDateTime fecha;
    private String codigo;


}
