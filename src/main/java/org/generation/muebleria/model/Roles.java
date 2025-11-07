package org.generation.muebleria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private Long idRol;
    @Column(name="nombre_rol", unique = true, nullable = false, length = 50)
    private String nombreRol;

    //Relacion: uno -> muchos (Usuarios)
    //(Usuarios) -> se definio [rol] para el mappedBy
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Usuarios> usuarios;

}