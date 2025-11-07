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
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    @Column(name="nombre", nullable = false, length = 150)
    private String nombre;
    @Column(name="apellidos", nullable = false, length = 150)
    private String apellidos;
    @Column(name="password_hash", nullable = false, length = 500)
    private String passwordHas;
    @Column(name="telefono", nullable = false, length = 150)
    private String telefono;
    @Column(name="correo", nullable = false, length = 150, unique = true)
    private String correo;
    @Column(name = "activo", columnDefinition = "TINYINT(1)")
    private Boolean activo = true;
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    //Relacion: muchos -> uno (Roles)
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Roles rol;

    //Relacion: uno -> muchos (Resenas)
    //(Resenas) -> se definio [usuario] para el mappedBy
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Resenas> resenas;

    //Relacion: uno -> muchos (Pedidos)
    //(Pedidos) -> se definio [usuario] para el mappedBy
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Pedidos> pedidos;

    //Relacion: uno -> muchos (Direccion)
    //(Direccion) -> se definio [usuario] para el mappedBy
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Direccion> direcciones;
}
