package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.CategoriaRequest;
import org.generation.muebleria.model.Categorias;
import org.generation.muebleria.repository.CategoriaRepository;
import org.generation.muebleria.service.interfaces.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // Inyecta el repositorio automáticamente
public class CategoriaService implements ICategoriaService {

    public CategoriaRepository categoriaRepository;

    @Override
    public List<Categorias> getAllCategoriasActive() {
        return categoriaRepository.findByActivoTrue();
    }

//    @Override
//    public List<Categorias> getRootCategoriasActive() {
//        return categoriaRepository.findByCategoriaPadreIsNullAndActivoTrue();
//    }

//    @Override
//    public List<Categorias> getSubCategoriasActive(Long idPadre) {
//        return categoriaRepository.findByCategoriaPadreIdCategoriaAndActivoTrue(idPadre);
//    }

    @Override
    public Optional<Categorias> getCategoriaById(Long id) {
        return Optional.ofNullable(categoriaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La categoria con el id: " + id + " no existe")
        ));
    }

    private Optional<Categorias> findPadre(Long idPadre){
        return (idPadre != null) ? categoriaRepository.findById(idPadre) : Optional.empty();
    }

    @Override
    public Categorias addCategoria(CategoriaRequest categoria) {
        if(categoriaRepository.findByNombreCategoria(categoria.getNombreCategoria()).isPresent()){
            throw new IllegalArgumentException("Ya existe una categoria con ese nombre");
        }
        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setNombreCategoria(categoria.getNombreCategoria());

        findPadre(categoria.getIdCategoriaPadre()).ifPresent(nuevaCategoria::setCategoriaPadre);

        return categoriaRepository.save(nuevaCategoria);
    }

    @Override
    public Categorias updateCategoriaById(Long id, CategoriaRequest categoriaActualizada) {
        // 1. Buscamos la categoría existente por ID
        Categorias categoriaExistente = categoriaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Categoria no encontrada")
        );

        if (categoriaActualizada.getNombreCategoria() != null) {
            // Re-validar unicidad, asegurando que no sea el mismo ID
            Optional<Categorias> byName = categoriaRepository.findByNombreCategoria(categoriaActualizada.getNombreCategoria());
            if (byName.isPresent() && !byName.get().getIdCategoria().equals(id)) {
                throw new IllegalArgumentException("Ya existe otra categoría con ese nombre.");
            }
            categoriaExistente.setNombreCategoria(categoriaActualizada.getNombreCategoria());
        }

        // Actualizar relación padre
        if (categoriaActualizada.getIdCategoriaPadre() != null) {
            Categorias padre = categoriaRepository.findById(categoriaActualizada.getIdCategoriaPadre())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría padre no encontrada."));

            // Buena práctica: evitar que una categoría sea su propia padre o forme un ciclo
            if (padre.getIdCategoria().equals(id)) {
                throw new IllegalArgumentException("Una categoría no puede ser su propia padre.");
            }
            categoriaExistente.setCategoriaPadre(padre);
        }

        // Actualizar estado activo
        if (categoriaActualizada.getActivo() != null) {
            categoriaExistente.setActivo(categoriaActualizada.getActivo());
        }

        return categoriaRepository.save(categoriaExistente);
    }

//    @Override
//    public Categorias deleteCategoriaById(Long id) {
//        // Esta es la implementación de "Soft Delete" (borrado lógico)
//        // que tu ejemplo de ProductoService parece implicar.
//
//        Optional<Categorias> categoriaOptional = categoriaRepository.findById(id);
//
//        if (categoriaOptional.isEmpty()) {
//            return null;
//        }
//
//        Categorias categoria = categoriaOptional.get();
//        categoria.setActivo(false); // 1. Marcamos como inactivo
//
//        return categoriaRepository.save(categoria); // 2. Guardamos el cambio
//    }

    @Override
    public void desactivarCategoriaById(Long id) {
        Categorias categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Categoría no encontrada con ID: " + id));
        if (categoria.getActivo()) {
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
        }
    }

    @Override
    public void activarCategoriaById(Long id) {
        Categorias categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Categoría no encontrada con ID: " + id));
        if (!categoria.getActivo()) {
            categoria.setActivo(true);
            categoriaRepository.save(categoria);
        }
    }

}