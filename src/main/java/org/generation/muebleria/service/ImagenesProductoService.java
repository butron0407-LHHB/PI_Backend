package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
// COMENTA TEMPORALMENTE ESTOS IMPORTS:
import org.generation.muebleria.dto.request.ImagenProductoRequest;
import org.generation.muebleria.dto.response.ImagenProductoResponse;
import org.generation.muebleria.model.ImagenesProducto;
import org.generation.muebleria.model.Productos;
import org.generation.muebleria.repository.ImagenesProductoRepository;
// COMENTA TEMPORALMENTE ESTA INTERFAZ:
import org.generation.muebleria.repository.ProductoRepository;
import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImagenesProductoService implements IImagenesProductoService {
    private final ImagenesProductoRepository imagenesProductoRepository;
    private final ProductoRepository productoRepository;

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
    public ImagenProductoResponse addImagen(ImagenProductoRequest request) {
        Productos producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + request.getIdProducto() + " no encontrado."));
        ImagenesProducto nuevaImagen = new ImagenesProducto();
        nuevaImagen.setUrlImagen(request.getUrlImagen());
        nuevaImagen.setProducto(producto);

        ImagenesProducto saveImagen = imagenesProductoRepository.save(nuevaImagen);

        return mapToResponseDTO(saveImagen);
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


    // ✅ MÉTODO TEMPORAL PARA QUE EL SERVICIO EXISTA
    //public String servicioTemporal() {
    //    return "Servicio de imágenes temporalmente deshabilitado";
    //}
}