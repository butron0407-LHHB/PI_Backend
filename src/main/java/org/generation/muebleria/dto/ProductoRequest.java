package org.generation.muebleria.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductoRequest {
    private String producto;
    private String sku;
    private String descripcion;
    private BigDecimal precioActual;
    private BigDecimal alto;
    private BigDecimal ancho;
    private BigDecimal profundidad;
    private BigDecimal peso;
    private Integer stockDisponible;

    //ID asignados desde Postman
    private Long idCategoria;
    private Long idProveedor;
}
