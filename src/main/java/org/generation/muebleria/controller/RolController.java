package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.model.Roles;
import org.generation.muebleria.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;

    // GET - Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // GET - Obtener rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRolById(@PathVariable Long id) {
        Roles rol = rolService.getRolById(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    // GET - Obtener rol por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Roles> getRolByNombre(@PathVariable String nombre) {
        Roles rol = rolService.getRolByNombre(nombre);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    // POST - Crear nuevo rol
    @PostMapping
    public ResponseEntity<Roles> createRol(@RequestBody Roles rol) {
        Roles nuevoRol = rolService.createRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    // PUT - Actualizar rol por ID
    @PutMapping("/{id}")
    public ResponseEntity<Roles> updateRolById(@PathVariable Long id, @RequestBody Roles rolActualizado) {
        Roles rol = rolService.updateRolById(id, rolActualizado);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    // DELETE - Eliminar rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolById(@PathVariable Long id) {
        Roles rolExistente = rolService.getRolById(id);
        if (rolExistente == null) {
            return ResponseEntity.notFound().build();
        }
        rolService.deleteRolById(id);
        return ResponseEntity.noContent().build();
    }
}