package org.generation.muebleria.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 50)
    private String nombreRol;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    // Los métodos deberían ser generados automáticamente por Lombok:
    // getNombreRol(), setNombreRol(), getDescripcion(), setDescripcion()
}