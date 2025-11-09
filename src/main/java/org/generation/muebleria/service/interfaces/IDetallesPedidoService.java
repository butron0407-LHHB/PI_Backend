package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.DetallesPedidoRequest;
import org.generation.muebleria.model.DetallesPedido;

import java.util.List;
import java.util.Optional;

public interface IDetallesPedidoService {

    // Trae todos los detalles de pedido
    List<DetallesPedido> getAllDetalles();

    // Trae detalle por Id
    Optional<DetallesPedido> getDetalleById(Long id);

    // Trae todos los detalles de un pedido específico
    List<DetallesPedido> getDetallesByPedido(Long idPedido);

    // Trae todos los detalles que contienen un producto específico
    List<DetallesPedido> getDetallesByProducto(Long idProducto);

    // Agregar detalle de pedido
    DetallesPedido addDetalle(DetallesPedidoRequest detalleRequest);

    // Actualizar detalle de pedido
    DetallesPedido updateDetalleById(Long id, DetallesPedidoRequest updateDetalleRequest);

    // Eliminar detalle de pedido
    void deleteDetalleById(Long id);
}