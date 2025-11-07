package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.model.DetallesPedido;
import java.util.List;

public interface IDetallesPedidoService {

    //List<DetallesPedido> getAllDetallesPedidoActive();

    DetallesPedido getDetallesPedidoById(Long id);

    DetallesPedido createDetallesPedido(DetallesPedido detallesPedido);

    DetallesPedido updateDetallesPedidoById(Long id, DetallesPedido updateDetallesPedido);

    DetallesPedido deleteDetallesPedidoById(Long id);
}