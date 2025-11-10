package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.DetallesPedidoRequest;
import org.generation.muebleria.model.DetallesPedido;
import org.generation.muebleria.service.interfaces.IDetallesPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/detalles-pedido")
@AllArgsConstructor
public class DetallesPedidoController {

    private final IDetallesPedidoService detallesPedidoService;

    // Obtener todos los detalles de pedidos
    // GET -> http://localhost:8080/api/detalles-pedido
    @GetMapping
    public ResponseEntity<List<DetallesPedido>> getAllDetalles() {
        List<DetallesPedido> detalles = detallesPedidoService.getAllDetalles();
        return ResponseEntity.ok(detalles);
    }

    // Obtener detalles por ID de pedido
    // GET -> http://localhost:8080/api/detalles-pedido/pedido/{pedidoId}
    @GetMapping(path = "/pedido/{pedidoId}")
    public ResponseEntity<List<DetallesPedido>> getDetallesByPedido(@PathVariable("pedidoId") Long idPedido) {
        List<DetallesPedido> detalles = detallesPedidoService.getDetallesByPedido(idPedido);
        return ResponseEntity.ok(detalles);
    }

    // Obtener detalles por ID de producto
    // GET -> http://localhost:8080/api/detalles-pedido/producto/{productoId}
    @GetMapping(path = "/producto/{productoId}")
    public ResponseEntity<List<DetallesPedido>> getDetallesByProducto(@PathVariable("productoId") Long idProducto) {
        List<DetallesPedido> detalles = detallesPedidoService.getDetallesByProducto(idProducto);
        return ResponseEntity.ok(detalles);
    }

    // Obtener detalle específico por su ID
    // GET -> http://localhost:8080/api/detalles-pedido/{detalleId}
    @GetMapping(path = "/{detalleId}")
    public ResponseEntity<DetallesPedido> getDetalleById(@PathVariable("detalleId") Long id) {
        Optional<DetallesPedido> detalle = detallesPedidoService.getDetalleById(id);
        return detalle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo detalle de pedido
    // POST -> http://localhost:8080/api/detalles-pedido
    @PostMapping
    public ResponseEntity<DetallesPedido> addDetalle(@RequestBody DetallesPedidoRequest detalle) {
        DetallesPedido nuevoDetalle = detallesPedidoService.addDetalle(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalle);
    }

    // Actualizar un detalle de pedido existente
    // PUT -> http://localhost:8080/api/detalles-pedido/{detalleId}
    @PutMapping(path = "/{detalleId}")
    public ResponseEntity<DetallesPedido> updateDetalle(
            @PathVariable("detalleId") Long id,
            @RequestBody DetallesPedidoRequest detalle) {
        try {
            DetallesPedido detalleActualizado = detallesPedidoService.updateDetalleById(id, detalle);
            return ResponseEntity.ok(detalleActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un detalle de pedido
    // DELETE -> http://localhost:8080/api/detalles-pedido/{detalleId}
    @DeleteMapping(path = "/{detalleId}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable("detalleId") Long id) {
        try {
            detallesPedidoService.deleteDetalleById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}