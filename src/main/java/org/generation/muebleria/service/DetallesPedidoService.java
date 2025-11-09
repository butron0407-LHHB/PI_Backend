package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.DetallesPedidoRequest;
import org.generation.muebleria.model.DetallesPedido;
import org.generation.muebleria.model.Pedidos;
import org.generation.muebleria.model.Productos;
import org.generation.muebleria.repository.DetallesPedidoRepository;
import org.generation.muebleria.repository.PedidosRepository;
import org.generation.muebleria.repository.ProductoRepository;
import org.generation.muebleria.service.interfaces.IDetallesPedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetallesPedidoService implements IDetallesPedidoService {

    // Inyección de dependencias
    private DetallesPedidoRepository detallesPedidoRepository;
    private PedidosRepository pedidosRepository;
    private ProductoRepository productoRepository;

    @Override
    public List<DetallesPedido> getAllDetalles() {
        return detallesPedidoRepository.findAll();
    }

    @Override
    public Optional<DetallesPedido> getDetalleById(Long id) {
        return Optional.ofNullable(detallesPedidoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El detalle de pedido con el id: " + id + " no existe")
        ));
    }

    @Override
    public List<DetallesPedido> getDetallesByPedido(Long idPedido) {
        return detallesPedidoRepository.findByPedidoIdPedido(idPedido);
    }

    @Override
    public List<DetallesPedido> getDetallesByProducto(Long idProducto) {
        return detallesPedidoRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public DetallesPedido addDetalle(DetallesPedidoRequest detalle) {
        Productos producto = productoRepository.findById(detalle.getIdProducto()).orElseThrow(
                () -> new IllegalArgumentException("El producto con ID " + detalle.getIdProducto() + " no existe.")
        );
        Pedidos pedido = pedidosRepository.findById(detalle.getIdPedido()).orElseThrow(
                () -> new IllegalArgumentException("El pedido con ID " + detalle.getIdPedido() + " no existe.")
        );

        DetallesPedido newDetalle = new DetallesPedido();
        if(detalle.getCantidad() != null) newDetalle.setCantidad(detalle.getCantidad());
        if(detalle.getPrecioUnitario() != null) newDetalle.setPrecioUnitario(detalle.getPrecioUnitario());
        if(detalle.getSubtotal() != null) newDetalle.setSubtotal(detalle.getSubtotal());

        newDetalle.setProducto(producto);
        newDetalle.setPedido(pedido);

        return detallesPedidoRepository.save(newDetalle);
    }

    @Override
    public DetallesPedido updateDetalleById(Long id, DetallesPedidoRequest updateDetalle) {
        Optional<DetallesPedido> optionalDetalle = detallesPedidoRepository.findById(id);
        if(optionalDetalle.isEmpty()) throw new IllegalArgumentException("El detalle de pedido no existe");

        // Obteniendo el detalle de la BD
        DetallesPedido detalleDB = optionalDetalle.get();
        if(updateDetalle.getCantidad() != null) detalleDB.setCantidad(updateDetalle.getCantidad());
        if(updateDetalle.getPrecioUnitario() != null) detalleDB.setPrecioUnitario(updateDetalle.getPrecioUnitario());
        if(updateDetalle.getSubtotal() != null) detalleDB.setSubtotal(updateDetalle.getSubtotal());

        if (updateDetalle.getIdProducto() != null) {
            Productos producto = productoRepository.findById(updateDetalle.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + updateDetalle.getIdProducto() + " no existe."));
            detalleDB.setProducto(producto);
        }

        if (updateDetalle.getIdPedido() != null) {
            Pedidos pedido = pedidosRepository.findById(updateDetalle.getIdPedido())
                    .orElseThrow(() -> new IllegalArgumentException("El pedido con ID " + updateDetalle.getIdPedido() + " no existe."));
            detalleDB.setPedido(pedido);
        }

        return detallesPedidoRepository.save(detalleDB);
    }

    @Override
    public void deleteDetalleById(Long id) {
        DetallesPedido detalle = detallesPedidoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El detalle de pedido con el id: " + id + " no existe")
        );
        detallesPedidoRepository.deleteById(id);
    }
}