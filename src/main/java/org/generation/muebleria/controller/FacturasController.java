package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.FacturaRequest;
import org.generation.muebleria.model.EstadoFactura;
import org.generation.muebleria.model.Facturas;
import org.generation.muebleria.service.interfaces.IFacturasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de facturas.
 * Provee endpoints para clientes y administradores.
 */
@RestController
@RequestMapping("/api/facturas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FacturasController {

    private final IFacturasService facturasService;

    // ========================================
    // ENDPOINTS PARA CLIENTES
    // ========================================

    /**
     * Solicitar factura para un pedido (durante checkout).
     * POST /api/facturas/solicitar
     *
     * @param request DTO con idPedido, RFC y Razón Social
     * @return Factura creada con estado PENDIENTE
     */
    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarFactura(@RequestBody FacturaRequest request) {
        try {
            Facturas factura = facturasService.crearFactura(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Obtener facturas de un usuario específico.
     * GET /api/facturas/usuario/{idUsuario}
     *
     * @param idUsuario ID del usuario
     * @return Lista de facturas del usuario
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Facturas>> obtenerFacturasPorUsuario(@PathVariable Long idUsuario) {
        List<Facturas> facturas = facturasService.obtenerFacturasPorUsuario(idUsuario);
        return ResponseEntity.ok(facturas);
    }

    /**
     * Obtener factura de un pedido específico.
     * GET /api/facturas/pedido/{idPedido}
     *
     * @param idPedido ID del pedido
     * @return Factura asociada al pedido
     */
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<?> obtenerFacturaPorPedido(@PathVariable Long idPedido) {
        try {
            Facturas factura = facturasService.obtenerFacturaPorPedido(idPedido);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ========================================
    // ENDPOINTS PARA ADMINISTRADORES
    // ========================================

    /**
     * Obtener todas las facturas del sistema.
     * GET /api/facturas/admin/todas
     *
     * @return Lista de todas las facturas
     */
    @GetMapping("/admin/todas")
    public ResponseEntity<List<Facturas>> obtenerTodasLasFacturas() {
        List<Facturas> facturas = facturasService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(facturas);
    }

    /**
     * Obtener facturas en estado PENDIENTE.
     * GET /api/facturas/admin/pendientes
     *
     * @return Lista de facturas pendientes de generar en el SAT
     */
    @GetMapping("/admin/pendientes")
    public ResponseEntity<List<Facturas>> obtenerFacturasPendientes() {
        List<Facturas> facturas = facturasService.obtenerFacturasPorEstado(EstadoFactura.PENDIENTE);
        return ResponseEntity.ok(facturas);
    }

    /**
     * Obtener facturas en estado GENERADA.
     * GET /api/facturas/admin/generadas
     *
     * @return Lista de facturas generadas pero no enviadas
     */
    @GetMapping("/admin/generadas")
    public ResponseEntity<List<Facturas>> obtenerFacturasGeneradas() {
        List<Facturas> facturas = facturasService.obtenerFacturasPorEstado(EstadoFactura.GENERADA);
        return ResponseEntity.ok(facturas);
    }

    /**
     * Obtener facturas en estado ENVIADA.
     * GET /api/facturas/admin/enviadas
     *
     * @return Lista de facturas enviadas al cliente
     */
    @GetMapping("/admin/enviadas")
    public ResponseEntity<List<Facturas>> obtenerFacturasEnviadas() {
        List<Facturas> facturas = facturasService.obtenerFacturasPorEstado(EstadoFactura.ENVIADA);
        return ResponseEntity.ok(facturas);
    }

    /**
     * Marcar factura como GENERADA.
     * PUT /api/facturas/admin/{id}/marcar-generada
     *
     * @param id ID de la factura
     * @return Factura actualizada
     */
    @PutMapping("/admin/{id}/marcar-generada")
    public ResponseEntity<?> marcarComoGenerada(@PathVariable Long id) {
        try {
            Facturas factura = facturasService.cambiarEstado(id, EstadoFactura.GENERADA);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Marcar factura como ENVIADA.
     * PUT /api/facturas/admin/{id}/marcar-enviada
     *
     * @param id ID de la factura
     * @return Factura actualizada
     */
    @PutMapping("/admin/{id}/marcar-enviada")
    public ResponseEntity<?> marcarComoEnviada(@PathVariable Long id) {
        try {
            Facturas factura = facturasService.cambiarEstado(id, EstadoFactura.ENVIADA);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Cambiar estado de una factura (endpoint genérico para admin).
     * PUT /api/facturas/admin/{id}/cambiar-estado
     *
     * @param id ID de la factura
     * @param estado Nuevo estado (PENDIENTE, GENERADA, ENVIADA)
     * @return Factura actualizada
     */
    @PutMapping("/admin/{id}/cambiar-estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoFactura estado) {
        try {
            Facturas factura = facturasService.cambiarEstado(id, estado);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
