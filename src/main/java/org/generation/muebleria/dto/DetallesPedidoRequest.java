package org.generation.muebleria.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DetallesPedidoRequest {

    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    // IDs asignados desde Postman
    private Long idProducto;
    private Long idPedido;
}