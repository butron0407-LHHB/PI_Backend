package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ProductoRequest;
import org.generation.muebleria.model.Productos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/productos")
@AllArgsConstructor
public class ProductoController {
    private final IProductoService productoService;

    // url -> /api/productos
    @GetMapping
    public List<Productos> getAllProductsActive(){
        return  productoService.getAllProductsActive();
    }

    //endPoint de administrador, para administrar productos
    //url -> /api/productos/admin/todos
    @GetMapping(path="/admin/todos")
    public List<Productos> getAllProducts(){
        return productoService.getAllProducts();
    }

    // url -> /api/productos/{productId}
    @GetMapping(path = "/{productId}")
    public Optional<Productos> getProductsById(@PathVariable("productId")Long id){
        return productoService.getProductsById(id);
    }

    // url -> /api/productos/admin/add
    @PostMapping(path="/admin/add")
    public Productos addPorduct(@RequestBody ProductoRequest product){
        return productoService.addProduct(product);
    }

    // url -> /api/productos/admin/update/{productId}
    @PutMapping(path ="/admin/update/{productId}")
    public Productos updateProductsById(@PathVariable("productId")Long id, @RequestBody ProductoRequest product){
        return productoService.updateProductsById(id,product);
    }

    @DeleteMapping(path="/admin/desactivar/{productId}")
    public void desactivarProductsById(@PathVariable("productId")Long id){
        productoService.desactivarProductsById(id);
    }

    @DeleteMapping(path="/admin/activar/{productId}")
    public void activarProductsById(@PathVariable("productId")Long id){
        productoService.activarProductsById(id);
    }

}
