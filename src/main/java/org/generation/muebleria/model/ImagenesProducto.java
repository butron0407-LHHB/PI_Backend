package org.generation.muebleria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="imagenes_producto")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImagenesProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private Long idImagen;
    @Column(name="url_imagen", nullable = false, length = 500)
    private String urlImagen;

    //Relacion muchos -> uno (Producto)
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonBackReference
    private Productos producto;

}