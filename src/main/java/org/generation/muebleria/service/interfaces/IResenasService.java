package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.ResenasRequest;
import org.generation.muebleria.model.Resenas;

import java.util.List;
import java.util.Optional;

public interface IResenasService {

    // Trae las reseñas visibles
    List<Resenas> getAllResenasVisible();

    // Trae todas las reseñas visibles y ocultas
    List<Resenas> getAllResenas();

    // Trae reseña por Id
    Optional<Resenas> getResenaById(Long id);

    // Trae todas las reseñas de un producto específico
    List<Resenas> getResenasByProducto(Long idProducto);

    // Trae todas las reseñas de un usuario específico
    List<Resenas> getResenasByUsuario(Long idUsuario);

    // Trae todas las reseñas de un pedido específico
    List<Resenas> getResenasByPedido(Long idPedido);

    // Agregar reseña
    Resenas addResena(ResenasRequest resenasRequest);

    // Actualizar reseña
    Resenas updateResenaById(Long id, ResenasRequest updateResenasRequest);

    // Ocultar reseña
    void ocultarResenaById(Long id);

    // Mostrar reseña
    void mostrarResenaById(Long id);

    // Eliminar reseña
    void deleteResenaById(Long id);
}