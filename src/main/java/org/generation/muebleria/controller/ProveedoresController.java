package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ProveedorRequest;
import org.generation.muebleria.model.Proveedores;
import org.generation.muebleria.service.ProveedoresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
@AllArgsConstructor
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

//    // POST - Crear proveedor
//    @PostMapping
//    public Proveedores crearProveedor(@RequestBody Proveedores proveedor) {
//        return proveedoresService.crearProveedor(proveedor);
//    }
//
//    // GET - Obtener todos los proveedores activos
//    @GetMapping("/activos")
//    public List<Proveedores> obtenerProveedoresActivos() {
//        return proveedoresService.obtenerProveedoresActivos();
//    }
//
//    // GET - Obtener todos los proveedores
//    @GetMapping
//    public List<Proveedores> obtenerTodosProveedores() {
//        return proveedoresService.obtenerTodosProveedores();
//    }
//
//    // GET - Obtener proveedor por ID
//    @GetMapping("/{id}")
//    public Proveedores obtenerProveedorPorId(@PathVariable Long id) {
//        return proveedoresService.obtenerProveedorPorId(id);
//    }
//
//    // PUT - Actualizar proveedor
//    @PutMapping("/{id}")
//    public Proveedores actualizarProveedor(@PathVariable Long id, @RequestBody Proveedores proveedor) {
//        return proveedoresService.actualizarProveedor(id, proveedor);
//    }
//
//    // PATCH - Desactivar proveedor
//    @PatchMapping("/{id}/desactivar")
//    public Proveedores desactivarProveedor(@PathVariable Long id) {
//        return proveedoresService.desactivarProveedor(id);
//    }

    // GET /api/proveedores
    @GetMapping
    public List<Proveedores> getAllActiveProviders(){
        return proveedoresService.getProveedoresActivos();
    }

    // GET /api/proveedores/{id}
    @GetMapping(path = "/{id}")
    public Optional<Proveedores> getProviderById(@PathVariable("id") Long id){
        return proveedoresService.getProveedorById(id);
    }

    // POST /api/proveedores/admin
    @PostMapping(path = "/admin/add")
    public Proveedores addProvider(@RequestBody ProveedorRequest proveedor){
        return proveedoresService.addProveedor(proveedor);
    }

    // PUT /api/proveedores/admin/{id}
    @PutMapping(path = "/admin/update/{id}")
    public Proveedores updateProvider(@PathVariable("id") Long id, @RequestBody ProveedorRequest proveedor){
        return proveedoresService.updateProveedor(id, proveedor);
    }

    // DELETE /api/proveedores/admin/desactivar/{id} (Borrado suave)
    @DeleteMapping(path = "/admin/desactivar/{id}")
    public void desactivarProvider(@PathVariable("id") Long id){
        proveedoresService.desactivarProveedorById(id);
    }

    // PUT /api/proveedores/admin/activate/{id}
    @DeleteMapping(path = "/admin/activate/{id}")
    public void activateProvider(@PathVariable Long id){
        proveedoresService.activarProveedorById(id);
    }
}