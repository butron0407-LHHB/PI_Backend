package org.generation.muebleria.controller;

import org.generation.muebleria.dto.DireccionRequest;
import org.generation.muebleria.model.Direccion;
import org.generation.muebleria.service.DireccionService;
import org.generation.muebleria.service.IDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionController {

    @Autowired
    private IDireccionService direccionService;

    // GET - Obtener todas las direcciones
    @GetMapping
    public ResponseEntity<List<Direccion>> getAllDirecciones() {
        List<Direccion> direcciones = direccionService.getAllDirecciones();
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    // GET - Obtener dirección por ID
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Long id) {
        Optional<Direccion> direccion = direccionService.getDireccionById(id);
        return direccion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET - Obtener direcciones por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Direccion>> getDireccionesByUsuario(@PathVariable Long idUsuario) {
        List<Direccion> direcciones = direccionService.getDireccionesByUsuario(idUsuario);
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    // GET - Obtener dirección predeterminada de un usuario
    @GetMapping("/usuario/{idUsuario}/predeterminada")
    public ResponseEntity<Direccion> getDireccionPredeterminada(@PathVariable Long idUsuario) {
        Optional<Direccion> direccion = direccionService.getDireccionPredeterminada(idUsuario);
        return direccion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST - Crear nueva dirección
    @PostMapping
    public ResponseEntity<Direccion> createDireccion(@RequestBody DireccionRequest direccionRequest) {
        try {
            Direccion nuevaDireccion = direccionService.createDireccion(direccionRequest);
            return new ResponseEntity<>(nuevaDireccion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT - Actualizar dirección existente
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(@PathVariable Long id,
                                                     @RequestBody DireccionRequest direccionRequest) {
        try {
            Direccion direccionActualizada = direccionService.updateDireccion(id, direccionRequest);
            if (direccionActualizada != null) {
                return new ResponseEntity<>(direccionActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH - Marcar dirección como predeterminada
    @PatchMapping("/{id}/predeterminada")
    public ResponseEntity<Direccion> setDireccionPredeterminada(@PathVariable Long id) {
        try {
            Direccion direccion = direccionService.setDireccionPredeterminada(id);
            if (direccion != null) {
                return new ResponseEntity<>(direccion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE - Eliminar dirección
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDireccion(@PathVariable Long id) {
        try {
            boolean eliminada = direccionService.deleteDireccion(id);
            if (eliminada) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET - Verificar si una dirección puede ser eliminada (sin pedidos asociados)
    @GetMapping("/{id}/puede-eliminar")
    public ResponseEntity<Boolean> puedeEliminarDireccion(@PathVariable Long id) {
        boolean puedeEliminar = direccionService.puedeEliminarDireccion(id);
        return new ResponseEntity<>(puedeEliminar, HttpStatus.OK);
    }
}