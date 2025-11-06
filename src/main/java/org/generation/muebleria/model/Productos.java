package org.generation.muebleria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="productos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    @Column(name="producto", nullable = false, length = 200)
    private String producto;
    @Column(name="sku", nullable = false, length = 200)
    private String sku;
    @Column(name="descripcion", length = 500)
    private String descripcion;
    @Column(name="precio_actual", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioActual;
    @Column(name="alto", nullable = false, precision = 10, scale = 2)
    private BigDecimal alto;
    @Column(name="ancho", nullable = false, precision = 10, scale = 2)
    private BigDecimal ancho;
    @Column(name="profundidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal profundidad;
    @Column(name="peso", nullable = false, precision = 10, scale = 2)
    private BigDecimal peso;
    @Column(name="activo", columnDefinition = "TINYINT(1)")
    private Boolean activo = true;
    @Column(name="stock_disponible")
    private Integer stockDisponible;
    @Column(name="fecha_pedido", updatable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

        //Relacion Muchos a Uno en Categoría
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categorias categoria;

    //Relacion Muchos a Uno Proveedores
    @ManyToOne
    @JoinColumn(name = "id_proveedores", nullable = false)
    private Proveedores proveedores;

    // --- Relación uno a muchos reseñas
    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Resenas> resenas;

    // --- Relación uno a muchos detalles pedido
    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<DetallesPedido> detallesPedido;

    // --- Relación uno a muchos detalles imagenes
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<ImagenesProducto> imagenesProducto;


}
