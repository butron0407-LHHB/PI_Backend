package org.generation.muebleria.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Long idPedido;

    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "costo_envio", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoEnvio;

    @Column(name = "numero_guia", nullable = false, length = 150)
    private String numeroGuia;

    @Column(name = "fecha_pedido", nullable = false, updatable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    //Relacion de uno a uno con factura
    @OneToOne
    @JoinColumn(name = "id_factura")
    @JsonIgnore
    private Facturas facturas;

    // --- Relación uno a muchos reseñas
    @OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<Resenas> resenas;

    // --- Relación uno a muchos detalles pedido
    @OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Lado "trasero" para evitar bucles
    private List<DetallesPedido> detallesPedidos;

    //Relacion de muchos a uno con usuarios
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuarios;

    //Relacion de muchos a uno con direcciones
    @ManyToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;
    

}


