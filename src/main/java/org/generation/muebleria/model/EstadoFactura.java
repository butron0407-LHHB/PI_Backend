package org.generation.muebleria.model;

/**
 * Enum para representar los posibles estados de una factura.
 *
 * PENDIENTE: Cliente solicitó factura, esperando generación por admin
 * GENERADA: Admin generó la factura en el SAT
 * ENVIADA: Admin envió el PDF/XML al cliente por email
 */
public enum EstadoFactura {
    PENDIENTE,
    GENERADA,
    ENVIADA
}
