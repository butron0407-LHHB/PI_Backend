package org.generation.muebleria.controller;

import org.generation.muebleria.dto.ImagenRequestDTO;
// Ya no importamos ImagenResponseDTO
import org.generation.muebleria.model.ImagenesProducto; // Importamos el Modelo
import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "*")
public class ImagenesProductoController {

    @Autowired
    private IImagenesProductoService imagenesProductoService;

    // GET: Obtener todas las imágenes de un producto
    // Cambiado: Devuelve ResponseEntity<List<ImagenesProducto>>
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ImagenesProducto>> obtenerImagenesPorProducto(
            @PathVariable("idProducto") Long idProducto) {

        List<ImagenesProducto> imagenes = imagenesProductoService.getImagesByProductId(idProducto);
        return ResponseEntity.ok(imagenes);
    }

    // POST: Crear una nueva imagen
    // Cambiado: Devuelve ResponseEntity<ImagenesProducto>
    @PostMapping
    public ResponseEntity<ImagenesProducto> crearImagen(
            @RequestBody ImagenRequestDTO imagenDTO) {

        try {
            ImagenesProducto nuevaImagen = imagenesProductoService.createImage(imagenDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaImagen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE: Borrar una imagen por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(
            @PathVariable("id") Long id) {

        try {
            imagenesProductoService.deleteImageById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}