package org.generation.muebleria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImagenRequestDTO {

    // El frontend solo nos envía la URL...
    private String urlImagen;

    // ...y el ID del producto al que pertenece
    private Long idProducto;
}