package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.ResenasRequest;
import org.generation.muebleria.model.Resenas;

import java.util.List;
import java.util.Optional;

public interface IResenasService {

    // Obtener todas las reseñas visibles
    List<Resenas> getAllResenasVisible();

    // Obtener reseñas por producto
    List<Resenas> getResenasByProducto(Long idProducto);

    // Obtener reseñas por usuario
    List<Resenas> getResenasByUsuario(Long idUsuario);

    // Obtener una reseña por ID
    Optional<Resenas> getResenaById(Long id);

    // Crear una nueva reseña
    Resenas addResena(ResenasRequest resena);
}