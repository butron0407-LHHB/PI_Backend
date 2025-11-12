package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
// COMENTA TEMPORALMENTE ESTOS IMPORTS:
// import org.generation.muebleria.dto.request.ImagenProductoRequest;
// import org.generation.muebleria.dto.response.ImagenProductoResponse;
// import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/imagenes")
@AllArgsConstructor
public class ImagenProductoController {

    // COMENTA TEMPORALMENTE LA INYECCIÓN:
    // private final IImagenesProductoService imagenService;

    // [GET] Todas las imágenes (Admin) - COMENTADO TEMPORALMENTE
    /*
    @GetMapping
    public List<ImagenProductoResponse> getAllImagenes() {
        return imagenService.getAllImagenes();
    }
    */

    // [GET] Imágenes por producto - COMENTADO TEMPORALMENTE
    /*
    @GetMapping("/producto/{idProducto}")
    public List<ImagenProductoResponse> getImagenesByProducto(@PathVariable("idProducto") Long idProducto) {
        return imagenService.getImagenesByProductoId(idProducto);
    }
    */

    // [GET] Una imagen por ID - COMENTADO TEMPORALMENTE
    /*
    @GetMapping("/{id}")
    public Optional<ImagenProductoResponse> getImagenById(@PathVariable("id") Long id) {
        return imagenService.getImagenById(id);
    }
    */

    // [POST] Añadir nueva imagen - COMENTADO TEMPORALMENTE
    /*
    @PostMapping(path = "/admin/add")
    public ImagenProductoResponse addImagen(@RequestBody ImagenProductoRequest imagenRequest) {
        return imagenService.addImagen(imagenRequest);
    }
    */

    // [PUT] Actualizar imagen - COMENTADO TEMPORALMENTE
    /*
    @PutMapping(path = "/admin/update/{id}")
    public ImagenProductoResponse updateImagen(@PathVariable("id") Long id, @RequestBody ImagenProductoRequest imagenRequest) {
        return imagenService.updateImagen(id, imagenRequest);
    }
    */

    // [DELETE] Eliminar imagen - COMENTADO TEMPORALMENTE
    /*
    @DeleteMapping(path="/admin/delete/{id}")
    public void deleteImagen(@PathVariable("id") Long id) {
        imagenService.deleteImagen(id);
    }
    */

    // ✅ ENDPOINT TEMPORAL PARA QUE NO DE ERROR 404
    @GetMapping
    public ResponseEntity<String> servicioTemporal() {
        return ResponseEntity.ok("Servicio de imágenes temporalmente deshabilitado");
    }
}