package org.generation.muebleria.service;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.ImagenRequestDTO;
// Ya no importamos ImagenResponseDTO
import org.generation.muebleria.model.ImagenesProducto;
import org.generation.muebleria.model.Productos;
import org.generation.muebleria.repository.ImagenesProductoRepository;
import org.generation.muebleria.repository.ProductoRepository;
import org.generation.muebleria.service.interfaces.IImagenesProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
// Ya no necesitamos Collectors

@Service
@AllArgsConstructor
public class ImagenesProductoService implements IImagenesProductoService {

    private final ImagenesProductoRepository imagenesProductoRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<ImagenesProducto> getImagesByProductId(Long idProducto) {
        // Simplemente devolvemos lo que encontramos (sin mapear)
        return imagenesProductoRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public ImagenesProducto getImageById(Long id) {
        // Devolvemos la entidad o null
        return imagenesProductoRepository.findById(id).orElse(null);
    }

    @Override
    public ImagenesProducto createImage(ImagenRequestDTO imagenDTO) {
        // 1. Buscar el Producto (el "dueño")
        Productos producto = productoRepository.findById(imagenDTO.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + imagenDTO.getIdProducto()));

        // 2. Crear la nueva Entidad
        ImagenesProducto nuevaImagen = new ImagenesProducto();
        nuevaImagen.setUrlImagen(imagenDTO.getUrlImagen());
        nuevaImagen.setProducto(producto);

        // 3. Guardar y DEVOLVER EL MODELO guardado
        return imagenesProductoRepository.save(nuevaImagen);
    }

    @Override
    public void deleteImageById(Long id) {
        if (!imagenesProductoRepository.existsById(id)) {
            throw new RuntimeException("Imagen no encontrada con id: " + id);
        }
        imagenesProductoRepository.deleteById(id);
    }
}