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

    // url -> /api/detalles-pedido
    @GetMapping
    public List<DetallesPedido> getAllDetalles(){
        return detallesPedidoService.getAllDetalles();
    }

    // url -> /api/detalles-pedido/{detalleId}
    @GetMapping(path = "/{detalleId}")
    public Optional<DetallesPedido> getDetalleById(@PathVariable("detalleId") Long id){
        return detallesPedidoService.getDetalleById(id);
    }

    // url -> /api/detalles-pedido/pedido/{pedidoId}
    @GetMapping(path = "/pedido/{pedidoId}")
    public List<DetallesPedido> getDetallesByPedido(@PathVariable("pedidoId") Long idPedido){
        return detallesPedidoService.getDetallesByPedido(idPedido);
    }

    // url -> /api/detalles-pedido/producto/{productoId}
    @GetMapping(path = "/producto/{productoId}")
    public List<DetallesPedido> getDetallesByProducto(@PathVariable("productoId") Long idProducto){
        return detallesPedidoService.getDetallesByProducto(idProducto);
    }

    // url -> /api/detalles-pedido/add
    @PostMapping(path="/add")
    public DetallesPedido addDetalle(@RequestBody DetallesPedidoRequest detalle){
        return detallesPedidoService.addDetalle(detalle);
    }

    // url -> /api/detalles-pedido/update/{detalleId}
    @PutMapping(path ="/update/{detalleId}")
    public DetallesPedido updateDetalleById(@PathVariable("detalleId") Long id, @RequestBody DetallesPedidoRequest detalle){
        return detallesPedidoService.updateDetalleById(id, detalle);
    }

    // url -> /api/detalles-pedido/delete/{detalleId}
    @DeleteMapping(path="/delete/{detalleId}")
    public void deleteDetalleById(@PathVariable("detalleId") Long id){
        detallesPedidoService.deleteDetalleById(id);
    }
}