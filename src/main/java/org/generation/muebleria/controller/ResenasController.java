package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ResenasRequest;
import org.generation.muebleria.model.Resenas;
import org.generation.muebleria.service.interfaces.IResenasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/resenas")
@AllArgsConstructor
public class ResenasController {

    private final IResenasService resenasService;

    //  Obtener todas las reseñas visibles
    // GET -> http://localhost:8080/api/resenas
    @GetMapping
    public List<Resenas> getAllResenasVisible() {
        return resenasService.getAllResenasVisible();
    }

    //  Obtener reseñas por producto
    // GET -> http://localhost:8080/api/resenas/producto/{productoId}
    @GetMapping(path = "/producto/{productoId}")
    public List<Resenas> getResenasByProducto(@PathVariable("productoId") Long idProducto) {
        return resenasService.getResenasByProducto(idProducto);
    }

    //  Obtener reseñas por usuario
    // GET -> http://localhost:8080/api/resenas/usuario/{usuarioId}
    @GetMapping(path = "/usuario/{usuarioId}")
    public List<Resenas> getResenasByUsuario(@PathVariable("usuarioId") Long idUsuario) {
        return resenasService.getResenasByUsuario(idUsuario);
    }

    //  Obtener una reseña específica
    // GET -> http://localhost:8080/api/resenas/{resenaId}
    @GetMapping(path = "/{resenaId}")
    public Optional<Resenas> getResenaById(@PathVariable("resenaId") Long id) {
        return resenasService.getResenaById(id);
    }

    //  Crear una nueva reseña
    // POST -> http://localhost:8080/api/resenas/add
    @PostMapping(path="/add")
    public Resenas addResena(@RequestBody ResenasRequest resena) {
        return resenasService.addResena(resena);
    }
}