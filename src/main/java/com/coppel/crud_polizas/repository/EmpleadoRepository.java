package com.coppel.crud_polizas.repository;

import com.coppel.crud_polizas.domain.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public List<Empleado> findAll();
    public Optional<Empleado> findById(Long id);
}
