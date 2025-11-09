package org.generation.muebleria.service.interfaces;

import org.generation.muebleria.dto.ImagenRequestDTO;
// Ya no importamos ImagenResponseDTO
import org.generation.muebleria.model.ImagenesProducto; // Importamos el Modelo
import java.util.List;

public interface IImagenesProductoService {

    // Cambiado: ahora devuelve una lista del Modelo
    List<ImagenesProducto> getImagesByProductId(Long idProducto);

    // Cambiado: ahora devuelve el Modelo
    ImagenesProducto getImageById(Long id);

    // Cambiado: recibe DTO, pero devuelve el Modelo
    ImagenesProducto createImage(ImagenRequestDTO imagenDTO);

    void deleteImageById(Long id);
}