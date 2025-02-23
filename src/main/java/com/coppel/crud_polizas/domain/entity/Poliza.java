package com.coppel.crud_polizas.domain.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_genero", nullable = false)
    private Empleado empleadoGenero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario_sku",nullable = false)
    private Inventario inventario;

    private int cantidad;

    private boolean activo;

    @Column(name = "fecha", updatable = false)
    private LocalDateTime fecha;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
