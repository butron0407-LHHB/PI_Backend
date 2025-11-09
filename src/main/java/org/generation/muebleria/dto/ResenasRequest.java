package org.generation.muebleria.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ResenasRequest {

    private Integer calificacion;
    private String comentario;
    private Boolean resenaVisible;
    private LocalDateTime fechaResena;

    // IDs asignados desde Postman
    private Long idProducto;
    private Long idUsuario;
    private Long idPedido;
}