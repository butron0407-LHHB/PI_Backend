package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ProductoRequest;
import org.generation.muebleria.model.Categorias;
import org.generation.muebleria.model.Productos;
import org.generation.muebleria.model.Proveedores;
import org.generation.muebleria.repository.CategoriaRepository;
import org.generation.muebleria.repository.ProductoRepository;
import org.generation.muebleria.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductoService implements IProductoService {

    //inyeccion de dependencia
    public ProductoRepository productoRepository;
    public CategoriaRepository categoriaRepository;
    public ProveedoresRepository proveedoresRepository;


    @Override
    public List<Productos> getAllProductsActive() {
        return productoRepository.findByActivoTrue();
    }

    @Override
    public List<Productos> getAllProducts() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Productos> getProductsById(Long id) {
        return Optional.ofNullable(productoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El porducto con el id: " + id + " no existe")
        ));
    }

    @Override
    public Productos addProduct(ProductoRequest producto) {
        Categorias categoria = categoriaRepository.findById(producto.getIdCategoria()).orElseThrow(
                () -> new IllegalArgumentException("La categoría con ID " + producto.getIdCategoria() + " no existe.")
        );
        Proveedores proveedor = proveedoresRepository.findById(producto.getIdProveedor()).orElseThrow(
                () -> new IllegalArgumentException("La categoría con ID " + producto.getIdProveedor() + " no existe.")
        );

        Productos newProducto = new Productos();
        if(producto.getProducto()!= null) newProducto.setProducto(producto.getProducto());
        if(producto.getSku() != null) newProducto.setSku(producto.getSku());
        if(producto.getDescripcion() != null) newProducto.setDescripcion(producto.getDescripcion());
        if(producto.getPrecioActual() != null) newProducto.setPrecioActual(producto.getPrecioActual());
        if(producto.getAlto() != null) newProducto.setAlto(producto.getAlto());
        if(producto.getAncho() != null) newProducto.setAncho(producto.getAncho());
        if(producto.getProfundidad() != null) newProducto.setProfundidad(producto.getProfundidad());
        if(producto.getPeso() != null) newProducto.setPeso(producto.getPeso());
        if(producto.getStockDisponible() != null) newProducto.setStockDisponible(producto.getStockDisponible());

        newProducto.setCategoria(categoria);
        newProducto.setProveedor(proveedor);

        return productoRepository.save(newProducto);
    }

    @Override
    public Productos updateProductsById(Long id, ProductoRequest updateProduct) {
        Optional<Productos> optionalProduct = productoRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto no existe");
        //obteniendo el producto de la bd
        Productos productDB = optionalProduct.get();
        if(updateProduct.getProducto() != null) productDB.setProducto(updateProduct.getProducto());
        if(updateProduct.getSku() != null) productDB.setSku(updateProduct.getSku());
        if(updateProduct.getDescripcion() != null) productDB.setDescripcion(updateProduct.getDescripcion());
        if(updateProduct.getPrecioActual() != null) productDB.setPrecioActual(updateProduct.getPrecioActual());
        if(updateProduct.getAlto() != null) productDB.setAlto(updateProduct.getAlto());
        if(updateProduct.getAncho() != null) productDB.setAncho(updateProduct.getAncho());
        if(updateProduct.getProfundidad() != null) productDB.setProfundidad(updateProduct.getProfundidad());
        if(updateProduct.getPeso() != null) productDB.setPeso(updateProduct.getPeso());
        //if(updateProduct.getActivo() != null) productDB.setActivo(updateProduct.getActivo());
        if(updateProduct.getStockDisponible() != null) productDB.setStockDisponible(updateProduct.getStockDisponible());

        if (updateProduct.getIdCategoria() != null) {
            Categorias categoria = categoriaRepository.findById(updateProduct.getIdCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("La categoría con ID " + updateProduct.getIdCategoria() + " no existe."));
            productDB.setCategoria(categoria);
        }

        if (updateProduct.getIdProveedor() != null) {
            Proveedores proveedor = proveedoresRepository.findById(updateProduct.getIdProveedor())
                    .orElseThrow(() -> new IllegalArgumentException("El proveedor con ID " + updateProduct.getIdProveedor() + " no existe."));
            productDB.setProveedor(proveedor);
        }

        //fecha creacion no se actualiza
        //fecha actualizacion se actualiza al momento de guardado
        productDB.setFechaActualizacion(LocalDateTime.now());
        return productoRepository.save(productDB);
    }

    @Override
    public void desactivarProductsById(Long id) {
        Productos producto = productoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El porducto con el id: " + id + " no existe")
        );

        //desactivar el producto con Activo
        if(producto.getActivo()){
            producto.setActivo(false);
            producto.setFechaActualizacion(LocalDateTime.now());
            productoRepository.save(producto);
        }else {
            throw new IllegalArgumentException("El producto con ID " + id + " ya está inactivo.");
        }

    }

    @Override
    public void activarProductsById(Long id) {
        Productos producto = productoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El porducto con el id: " + id + " no existe")
        );

        //activar producto
        if(producto.getActivo() == null || !producto.getActivo()){
            producto.setActivo(true);
            producto.setFechaActualizacion(LocalDateTime.now());
            productoRepository.save(producto);
        }else{
            throw new IllegalArgumentException("El producto con ID " + id + " ya está activo.");
        }
    }
}
