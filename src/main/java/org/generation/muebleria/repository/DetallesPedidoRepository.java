package org.generation.muebleria.repository;

import org.generation.muebleria.model.DetallesPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido,Long> {

    // Buscar todos los detalles de un pedido específico
    List<DetallesPedido> findByPedidoIdPedido(Long idPedido);

    // Buscar todos los detalles que contienen un producto específico
    List<DetallesPedido> findByProductoIdProducto(Long idProducto);
}