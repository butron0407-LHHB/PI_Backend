package org.generation.muebleria.repository;

import org.generation.muebleria.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

    //Métodopara listar solo categorías activas
    List<Categorias> findByActivoTrue();
    Optional<Categorias> findByNombreCategoria(String nombreCategoria);

    // Métodoutil para buscar subcategorías (hijas) de una categoría padre
    List<Categorias> findByCategoriaPadreIdCategoriaAndActivoTrue(Long idCategoriaPadre);

    // Métodoutil para buscar categorías principales (las que no tienen padre)
    List<Categorias> findByCategoriaPadreIsNullAndActivoTrue();

}