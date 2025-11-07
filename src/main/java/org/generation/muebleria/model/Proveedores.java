package org.generation.muebleria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;
    @Column(name = "nombre_empresa", nullable = false, length = 200)
    private String nombreEmpresa;
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;
    @Column(name = "telefono", nullable = false, length = 100)
    private String telefono;
    @Column(name = "correo", nullable = false, length = 200)
    private String correo;
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    @Column(name = "acivo", columnDefinition = "TINYINT(1)")
    private Boolean activo = true;
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
    @Column(name = "fecha_actualizacion", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    //Relacion uno -> mucho (Productos)
    //(Productos) -> se definio [proveedor] para el mappedBy
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Productos> productos;

}