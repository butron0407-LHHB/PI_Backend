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

    @Column(name="id_roll", nullable = false)
    private Integer idRoll;

    @Column(name="nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name="apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column(name="password_hash", nullable = false, length = 500)
    private String passwordHas;

    @Column(name="telefono", nullable = false, length = 150)
    private String telefono;

    @Column(name="email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    //Relacion Muchos a Uno roles
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Roles roles;

    // --- Relación uno a muchos reseñas
    @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Resenas> resenas;

    // --- Relación uno a muchos pedidos
    @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Pedidos> pedidos;

    // --- Relación uno a muchos direcciones
    @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Direccion> direccion;
}
