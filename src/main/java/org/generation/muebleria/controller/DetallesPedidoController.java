package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.DetallesPedidoRequest;
import org.generation.muebleria.model.DetallesPedido;
import org.generation.muebleria.service.interfaces.IDetallesPedidoService;
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
    public List<DetallesPedido> getAllDetalles() {
        return detallesPedidoService.getAllDetalles();
    }

    //  Obtener detalles por ID de pedido
    // GET -> http://localhost:8080/api/detalles-pedido/pedido/{pedidoId}
    @GetMapping(path = "/pedido/{pedidoId}")
    public List<DetallesPedido> getDetallesByPedido(@PathVariable("pedidoId") Long idPedido) {
        return detallesPedidoService.getDetallesByPedido(idPedido);
    }

    //  Obtener detalle específico por su ID
    // GET -> http://localhost:8080/api/detalles-pedido/{detalleId}
    @GetMapping(path = "/{detalleId}")
    public Optional<DetallesPedido> getDetalleById(@PathVariable("detalleId") Long id) {
        return detallesPedidoService.getDetalleById(id);
    }

    // Crear un nuevo detalle de pedido
    // POST -> http://localhost:8080/api/detalles-pedido/add
    @PostMapping(path="/add")
    public DetallesPedido addDetalle(@RequestBody DetallesPedidoRequest detalle) {
        return detallesPedidoService.addDetalle(detalle);
    }
}
