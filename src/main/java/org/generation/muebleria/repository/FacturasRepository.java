package org.generation.muebleria.repository;

import org.generation.muebleria.model.EstadoFactura;
import org.generation.muebleria.model.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Facturas.
 * Proporciona métodos de consulta personalizados.
 */
@Repository
public interface FacturasRepository extends JpaRepository<Facturas, Long> {

    /**
     * Busca una factura usando el ID del pedido asociado a la factura.
     * @param idPedido ID del pedido
     * @return Optional con la factura si existe
     */
    Optional<Facturas> findByPedido_IdPedido(Long idPedido);

    /**
     * Busca facturas por RFC.
     * @param rfc El RFC del cliente
     * @return Lista de facturas asociadas a ese RFC
     */
    List<Facturas> findByRfc(String rfc);

    /**
     * Busca todas las facturas con un estado específico.
     * Útil para que el admin vea facturas PENDIENTES, GENERADAS o ENVIADAS.
     * @param estado Estado de la factura (PENDIENTE, GENERADA, ENVIADA)
     * @return Lista de facturas con ese estado
     */
    List<Facturas> findByEstadoFactura(EstadoFactura estado);

    /**
     * Busca todas las facturas de un usuario específico.
     * Permite al cliente ver sus propias facturas.
     * @param idUsuario ID del usuario
     * @return Lista de facturas del usuario
     */
    List<Facturas> findByPedido_Usuario_IdUsuario(Long idUsuario);

    /**
     * Verifica si ya existe una factura para un pedido específico.
     * Evita duplicados ya que un pedido solo puede tener una factura.
     * @param idPedido ID del pedido
     * @return true si existe, false si no
     */
    boolean existsByPedido_IdPedido(Long idPedido);
}