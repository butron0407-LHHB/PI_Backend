package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.request.ImagenProductoRequest;
import org.generation.muebleria.dto.response.ImagenProductoResponse;
import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/imagenes")
@AllArgsConstructor
public class ImagenProductoController {

     private final IImagenesProductoService imagenService;

    // [GET] Todas las imágenes
    @GetMapping
    public List<ImagenProductoResponse> getAllImagenes() {
        return imagenService.getAllImagenes();
    }


    // [GET] Imágenes por producto
    @GetMapping("/producto/{idProducto}")
    public List<ImagenProductoResponse> getImagenesByProducto(@PathVariable("idProducto") Long idProducto) {
        return imagenService.getImagenesByProductoId(idProducto);
    }


    // [GET] Una imagen por ID
    @GetMapping("/{id}")
    public Optional<ImagenProductoResponse> getImagenById(@PathVariable("id") Long id) {
        return imagenService.getImagenById(id);
    }


    // [POST] Añadir nueva imagen
    @PostMapping(path = "/admin/add")
    public ImagenProductoResponse addImagen(@RequestBody ImagenProductoRequest imagenRequest) {
        return imagenService.addImagen(imagenRequest);
    }


    // [PUT] Actualizar imagen

    @PutMapping(path = "/admin/update/{id}")
    public ImagenProductoResponse updateImagen(@PathVariable("id") Long id, @RequestBody ImagenProductoRequest imagenRequest) {
        return imagenService.updateImagen(id, imagenRequest);
    }


    // [DELETE] Eliminar imagen - COMENTADO TEMPORALMENTE

    @DeleteMapping(path="/admin/delete/{id}")
    public void deleteImagen(@PathVariable("id") Long id) {
        imagenService.deleteImagen(id);
    }


//    // ENDPOINT TEMPORAL PARA QUE NO DE ERROR 404
//    @GetMapping
//    public ResponseEntity<String> servicioTemporal() {
//        return ResponseEntity.ok("Servicio de imágenes temporalmente deshabilitado");
//    }
}