package com.coppel.crud_polizas.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "polizas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poliza")
    private Long id;

    @NotNull(message = "El empleado es obligatorio")
    @ManyToOne
    @JoinColumn(name = "empleado_genero", nullable = false)
    private Empleado empleadoGenero;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Inventario inventario;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private boolean activo;

    @Column(name = "fecha", updatable = false)
    private LocalDateTime fecha;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
