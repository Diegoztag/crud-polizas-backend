package com.coppel.crud_polizas.repository;

import com.coppel.crud_polizas.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, String> {
    Optional<Inventario> findBySku(String sku);
}
