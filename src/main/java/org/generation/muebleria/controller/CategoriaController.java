package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.CategoriaRequest;
import org.generation.muebleria.model.Categorias;
import org.generation.muebleria.service.interfaces.ICategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final ICategoriaService categoriaService;

    // url -> /api/categorias
    @GetMapping
    public List<Categorias> getAllActiveCategorias(){
        return categoriaService.getAllCategoriasActive();
    }

    // GET /api/categorias/{categoriaId}
    @GetMapping(path = "/{categoriaId}")
    public Optional<Categorias> getCategoryById(@PathVariable("categoriaId")Long id){
        return categoriaService.getCategoriaById(id);
    }

    // url -> /api/categorias/admin
    @PostMapping(path = "/admin/add")
    public Categorias addCategory(@RequestBody CategoriaRequest categoria){
        return categoriaService.addCategoria(categoria);
    }

    // url -> /api/categorias/admin/update/{categoriaId}
    @PutMapping(path = "/admin/update/{categoriaId}")
    public Categorias updateCategory(@PathVariable("categoriaId")Long id, @RequestBody CategoriaRequest categoria){
        return categoriaService.updateCategoriaById(id, categoria);
    }

    // url -> /api/categorias/admin/desactivar/{categoriaId}
    @DeleteMapping(path = "/admin/desactivar/{categoriaId}")
    public void desactivarCategoria(@PathVariable("categoriaId")Long id){
        categoriaService.desactivarCategoriaById(id);
    }

    // PUT /api/categorias/admin/activar/{categoriaId}
    @DeleteMapping(path = "/admin/activar/{categoriaId}")
    public void activarCategoria(@PathVariable("categoriaId") Long id){
        categoriaService.activarCategoriaById(id);
    }

}
