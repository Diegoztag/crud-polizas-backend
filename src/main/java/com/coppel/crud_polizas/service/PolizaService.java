package com.coppel.crud_polizas.service;

import com.coppel.crud_polizas.dto.EmpleadoDTO;
import com.coppel.crud_polizas.dto.InventarioDTO;
import com.coppel.crud_polizas.dto.Poliza.PolizaResponseDTO;
import com.coppel.crud_polizas.dto.Poliza.PolizaDTO;
import com.coppel.crud_polizas.entity.Empleado;
import com.coppel.crud_polizas.entity.Inventario;
import com.coppel.crud_polizas.entity.Poliza;
import com.coppel.crud_polizas.repository.EmpleadoRepository;
import com.coppel.crud_polizas.repository.InventarioRepository;
import com.coppel.crud_polizas.repository.PolizaRepository;
import com.coppel.crud_polizas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PolizaService {
    private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<PolizaResponseDTO> obtenerPolizas() {
        List<Poliza> polizas = polizaRepository.findByActivoTrue();

        return polizas.stream().map(poliza -> new PolizaResponseDTO(
                new PolizaDTO(poliza.getId(), poliza.getCantidad()),
                new EmpleadoDTO(poliza.getEmpleadoGenero().getNombre(), poliza.getEmpleadoGenero().getApellido()),
                new InventarioDTO(poliza.getInventario().getSku(), poliza.getInventario().getNombre())
        )).toList();
    }

    public PolizaResponseDTO obtenerPolizaPorId(Long id) {
        Poliza poliza = polizaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Póliza no encontrada con ID: " + id));

        return new PolizaResponseDTO(
                new PolizaDTO(poliza.getId(), poliza.getCantidad()),
                new EmpleadoDTO(poliza.getEmpleadoGenero().getNombre(), poliza.getEmpleadoGenero().getApellido()),
                new InventarioDTO(poliza.getInventario().getSku(), poliza.getInventario().getNombre()));
    }

    @Transactional
    public PolizaResponseDTO crearPoliza(Long idEmpleado, String sku, int cantidad) {
        logger.info("Intentando crear póliza para empleado {} y SKU {}", idEmpleado, sku);

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));

        Inventario inventario = inventarioRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado en inventario"));

        if (inventario.getCantidad() < cantidad) {
            throw new IllegalStateException("Stock insuficiente en inventario");
        }

        inventario.setCantidad(inventario.getCantidad() - cantidad);
        inventarioRepository.save(inventario);

        Poliza poliza = new Poliza();
        poliza.setEmpleadoGenero(empleado);
        poliza.setInventario(inventario);
        poliza.setCantidad(cantidad);
        poliza.setActivo(true);
        poliza.setFecha(LocalDateTime.now());
        poliza.setCreatedAt(LocalDateTime.now());

        polizaRepository.save(poliza);
        logger.info("Póliza creada con éxito ID: {}", poliza.getId());

        return new PolizaResponseDTO(
                new PolizaDTO(poliza.getId(), poliza.getCantidad()),
                new EmpleadoDTO(empleado.getNombre(), empleado.getApellido()),
                new InventarioDTO(inventario.getSku(), inventario.getNombre())
        );
    }

    @Transactional
    public void eliminarPoliza(Long idPoliza) {
        Poliza poliza = polizaRepository.findById(idPoliza)
                .orElseThrow(() -> new ResourceNotFoundException("Póliza no encontrada con ID: " + idPoliza));

        poliza.setActivo(false);
        polizaRepository.save(poliza);
        logger.info("Póliza con ID {} ha sido eliminada lógicamente.", idPoliza);
    }

    @Transactional
    public PolizaDTO actualizarEmpleadoEnPoliza(Long idPoliza, Long idEmpleado) {

        Poliza poliza = polizaRepository.findById(idPoliza)
                .orElseThrow(() -> new ResourceNotFoundException("Póliza no encontrada"));

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));

        poliza.setEmpleadoGenero(empleado);
        polizaRepository.save(poliza);
        logger.info("Póliza con id: {} actualizada con éxito", idPoliza);

        return new PolizaDTO(poliza.getId(), poliza.getCantidad());
    }
}
