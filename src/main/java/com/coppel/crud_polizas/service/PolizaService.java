package com.coppel.crud_polizas.service;

import com.coppel.crud_polizas.entity.Empleado;
import com.coppel.crud_polizas.entity.Inventario;
import com.coppel.crud_polizas.entity.Poliza;
import com.coppel.crud_polizas.repository.EmpleadoRepository;
import com.coppel.crud_polizas.repository.InventarioRepository;
import com.coppel.crud_polizas.repository.PolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolizaService {
    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Poliza> obtenerPolizas() {
        return polizaRepository.findAll();
    }
    public Poliza crearPoliza(Long idEmpleado, String sku, int cantidad) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(idEmpleado);
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(sku);

        if (empleadoOpt.isEmpty() || inventarioOpt.isEmpty()) {
            throw new RuntimeException("Empleado o inventario no encontrado");
        }

        Inventario inventario = inventarioOpt.get();
        if (inventario.getCantidad() < cantidad) {
            throw new RuntimeException("No hay suficiente inventario disponible.");
        }

        inventario.setCantidad(inventario.getCantidad() - cantidad);
        inventarioRepository.save(inventario);

        Poliza poliza = new Poliza();
        poliza.setEmpleadoGenero(empleadoOpt.get());
        poliza.setInventario(inventario);
        poliza.setCantidad(cantidad);

        return polizaRepository.save(poliza);
    }

    public void eliminarPoliza(Long idPoliza) {
        polizaRepository.deleteById(idPoliza);
    }

    public Poliza actualizarEmpleadoEnPoliza(Long idPoliza, Long nuevoEmpleadoId) {
        Optional<Poliza> polizaOpt = polizaRepository.findById(idPoliza);
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(nuevoEmpleadoId);

        if (polizaOpt.isEmpty() || empleadoOpt.isEmpty()) {
            throw new RuntimeException("Poliza o empleado no encontrado");
        }

        Poliza poliza = polizaOpt.get();
        poliza.setEmpleadoGenero(empleadoOpt.get());

        return polizaRepository.save(poliza);
    }
}
