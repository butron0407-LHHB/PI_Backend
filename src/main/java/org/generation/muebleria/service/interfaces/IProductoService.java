package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.ProductoRequest;
import org.generation.muebleria.model.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    //trae los productos activos
    List<Productos> getAllProductsActive();
    //trae todos los productos activos e inactivos
    List<Productos> getAllProducts();
    //trae productos por Id
    Optional<Productos> getProductsById(Long id);
    //agregar producto
    Productos addProduct(ProductoRequest productoDto);
    //actualizar producto
    Productos updateProductsById(Long id, ProductoRequest updateProductDto);
    //borrar producto o en este caso desactivar
    void desactivarProductsById(Long id);
    void activarProductsById(Long id);
}
