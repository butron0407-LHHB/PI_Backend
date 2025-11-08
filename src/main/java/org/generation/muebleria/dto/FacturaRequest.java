package org.generation.muebleria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para la solicitud de facturación por parte del cliente.
 * Se usa durante el checkout cuando el cliente decide facturar su pedido.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequest {

    /**
     * ID del pedido que se desea facturar
     */
    private Long idPedido;

    /**
     * RFC del cliente (Persona Física: 13 chars, Persona Moral: 12 chars)
     * No se valida en el MVP
     */
    private String rfc;

    /**
     * Razón social del cliente
     */
    private String razonSocial;

    // NOTA: subtotal, IVA y total se calculan automáticamente desde el pedido
}