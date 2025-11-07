package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.CategoriaRequest;
import org.generation.muebleria.model.Categorias;
import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    // Obtener todas las categorías activas
    List<Categorias> getAllCategoriasActive();

//    // Obtener las categorías principales (sin padre)
//    List<Categorias> getRootCategoriasActive();
//
//    // Obtener las subcategorías (hijas) de un padre
//    List<Categorias> getSubCategoriasActive(Long idPadre);

    // Obtener una categoría por ID
    Optional<Categorias> getCategoriaById(Long id);

    // Crear una nueva categoría
    Categorias addCategoria(CategoriaRequest categoria);

    // Actualizar una categoría
    Categorias updateCategoriaById(Long id, CategoriaRequest categoriaActualizada);

//    // Borrar una categoría (lógica de "soft delete")
//    Categorias deleteCategoriaById(Long id);

    void desactivarCategoriaById(Long id);
    void activarCategoriaById(Long id);
}