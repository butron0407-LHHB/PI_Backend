package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
// COMENTA TEMPORALMENTE ESTOS IMPORTS:
// import org.generation.muebleria.dto.request.ImagenProductoRequest;
// import org.generation.muebleria.dto.response.ImagenProductoResponse;
import org.generation.muebleria.model.ImagenesProducto;
import org.generation.muebleria.repository.ImagenesProductoRepository;
// COMENTA TEMPORALMENTE ESTA INTERFAZ:
// import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImagenesProductoService // COMENTA TEMPORALMENTE: implements IImagenesProductoService
{
    private final ImagenesProductoRepository imagenesProductoRepository;

    // COMENTA TEMPORALMENTE TODOS LOS MÉTODOS QUE USAN ImagenProductoResponse:

    /*
    @Override
    public List<ImagenProductoResponse> getAllImagenes() {
        List<ImagenesProducto> imagenes = imagenesProductoRepository.findAll();
        return imagenes.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImagenProductoResponse> getImagenesByProductoId(Long productoId) {
        List<ImagenesProducto> imagenes = imagenesProductoRepository.findByProductoIdProducto(productoId);
        return imagenes.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ImagenProductoResponse> getImagenById(Long id) {
        Optional<ImagenesProducto> imagen = imagenesProductoRepository.findById(id);
        return imagen.map(this::mapToResponseDTO);
    }

    @Override
    public ImagenProductoResponse addImagen(ImagenProductoRequest imagenRequest) {
        ImagenesProducto nuevaImagen = new ImagenesProducto();
        // ... lógica de guardado
        ImagenesProducto imagenGuardada = imagenesProductoRepository.save(nuevaImagen);
        return mapToResponseDTO(imagenGuardada);
    }

    @Override
    public ImagenProductoResponse updateImagen(Long id, ImagenProductoRequest imagenRequest) {
        Optional<ImagenesProducto> imagenExistente = imagenesProductoRepository.findById(id);
        if (imagenExistente.isPresent()) {
            ImagenesProducto imagen = imagenExistente.get();
            // ... lógica de actualización
            ImagenesProducto imagenActualizada = imagenesProductoRepository.save(imagen);
            return mapToResponseDTO(imagenActualizada);
        }
        throw new IllegalArgumentException("Imagen no encontrada");
    }

    @Override
    public void deleteImagen(Long id) {
        if (imagenesProductoRepository.existsById(id)) {
            imagenesProductoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Imagen no encontrada");
        }
    }

    @Override
    public ImagenProductoResponse mapToResponseDTO(ImagenesProducto imagen) {
        ImagenProductoResponse dto = new ImagenProductoResponse();
        dto.setIdImagen(imagen.getIdImagen());
        dto.setUrlImagen(imagen.getUrlImagen());
        return dto;
    }
    */

    // ✅ MÉTODO TEMPORAL PARA QUE EL SERVICIO EXISTA
    public String servicioTemporal() {
        return "Servicio de imágenes temporalmente deshabilitado";
    }
}