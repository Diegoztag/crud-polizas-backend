package com.coppel.crud_polizas.repository;

import com.coppel.crud_polizas.domain.entity.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {
    List<Poliza> findByActivoTrue();
}
