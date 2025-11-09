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

    // url -> /api/resenas
    @GetMapping
    public List<Resenas> getAllResenasVisible(){
        return resenasService.getAllResenasVisible();
    }

    // endPoint de administrador, para administrar reseñas
    // url -> /api/resenas/admin/todas
    @GetMapping(path="/admin/todas")
    public List<Resenas> getAllResenas(){
        return resenasService.getAllResenas();
    }

    // url -> /api/resenas/{resenaId}
    @GetMapping(path = "/{resenaId}")
    public Optional<Resenas> getResenaById(@PathVariable("resenaId") Long id){
        return resenasService.getResenaById(id);
    }

    // url -> /api/resenas/producto/{productoId}
    @GetMapping(path = "/producto/{productoId}")
    public List<Resenas> getResenasByProducto(@PathVariable("productoId") Long idProducto){
        return resenasService.getResenasByProducto(idProducto);
    }

    // url -> /api/resenas/usuario/{usuarioId}
    @GetMapping(path = "/usuario/{usuarioId}")
    public List<Resenas> getResenasByUsuario(@PathVariable("usuarioId") Long idUsuario){
        return resenasService.getResenasByUsuario(idUsuario);
    }

    // url -> /api/resenas/pedido/{pedidoId}
    @GetMapping(path = "/pedido/{pedidoId}")
    public List<Resenas> getResenasByPedido(@PathVariable("pedidoId") Long idPedido){
        return resenasService.getResenasByPedido(idPedido);
    }

    // url -> /api/resenas/admin/add
    @PostMapping(path="/admin/add")
    public Resenas addResena(@RequestBody ResenasRequest resena){
        return resenasService.addResena(resena);
    }

    // url -> /api/resenas/admin/update/{resenaId}
    @PutMapping(path ="/admin/update/{resenaId}")
    public Resenas updateResenaById(@PathVariable("resenaId") Long id, @RequestBody ResenasRequest resena){
        return resenasService.updateResenaById(id, resena);
    }

    // url -> /api/resenas/admin/ocultar/{resenaId}
    @DeleteMapping(path="/admin/ocultar/{resenaId}")
    public void ocultarResenaById(@PathVariable("resenaId") Long id){
        resenasService.ocultarResenaById(id);
    }

    // url -> /api/resenas/admin/mostrar/{resenaId}
    @PutMapping(path="/admin/mostrar/{resenaId}")
    public void mostrarResenaById(@PathVariable("resenaId") Long id){
        resenasService.mostrarResenaById(id);
    }

    // url -> /api/resenas/admin/delete/{resenaId}
    @DeleteMapping(path="/admin/delete/{resenaId}")
    public void deleteResenaById(@PathVariable("resenaId") Long id){
        resenasService.deleteResenaById(id);
    }
}