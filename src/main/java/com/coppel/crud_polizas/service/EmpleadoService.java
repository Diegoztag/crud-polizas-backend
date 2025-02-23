package com.coppel.crud_polizas.service;

import com.coppel.crud_polizas.domain.dto.EmpleadoDTO;
import com.coppel.crud_polizas.domain.entity.Empleado;
import com.coppel.crud_polizas.exceptions.ResourceNotFoundException;
import com.coppel.crud_polizas.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoDTO> obtenerEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();

        return empleados.stream().map(empleado -> new EmpleadoDTO(
                        empleado.getId(),
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getPuesto())
        ).toList();
    }

    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));

        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getPuesto());
    }

}
