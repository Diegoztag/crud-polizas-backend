package com.coppel.crud_polizas.service;

import com.coppel.crud_polizas.domain.dto.PolizaResponseDTO;
import com.coppel.crud_polizas.domain.entity.Empleado;
import com.coppel.crud_polizas.domain.entity.Inventario;
import com.coppel.crud_polizas.domain.entity.Poliza;
import com.coppel.crud_polizas.exceptions.ResourceNotFoundException;
import com.coppel.crud_polizas.repository.EmpleadoRepository;
import com.coppel.crud_polizas.repository.InventarioRepository;
import com.coppel.crud_polizas.repository.PolizaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PolizaServiceTest {

    @Mock
    private PolizaRepository polizaRepository;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private PolizaService polizaService;
    private Poliza poliza;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Empleado empleado = new Empleado(1L, "Juan", "Pérez", "Gerente" , LocalDateTime.now());
        Inventario inventario = new Inventario("SKU123", "Producto A", 10, LocalDateTime.now());
        poliza = new Poliza(1L, empleado, inventario, 5, true, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void testObtenerPolizas() {
        when(polizaRepository.findByActivoTrue()).thenReturn(List.of(poliza));
        List<PolizaResponseDTO> result = polizaService.obtenerPolizas();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPolizaPorId_Existente() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));
        PolizaResponseDTO result = polizaService.obtenerPolizaPorId(1L);
        assertNotNull(result);
        assertEquals(poliza.getId(), result.getPoliza().getIdPoliza());
    }

    @Test
    void testObtenerPolizaPorId_NoExistente() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> polizaService.obtenerPolizaPorId(1L));
    }

    @Test
    void testCrearPolizaExito() {
        Long idEmpleado = 1L;
        String sku = "SKU123";
        int cantidad = 5;

        Empleado empleado = new Empleado(idEmpleado, "Juan", "Perez", "Gerente", LocalDateTime.now());
        Inventario inventario = new Inventario(sku, "Producto A", 10, LocalDateTime.now());
        Poliza poliza = new Poliza(1L, empleado, inventario, cantidad, true, LocalDateTime.now(), LocalDateTime.now());

        when(empleadoRepository.findById(idEmpleado)).thenReturn(Optional.of(empleado));
        when(inventarioRepository.findBySku(sku)).thenReturn(Optional.of(inventario));
        when(polizaRepository.save(any(Poliza.class))).thenReturn(poliza);

        PolizaResponseDTO response = polizaService.crearPoliza(idEmpleado, sku, cantidad);

        assertNotNull(response);
        assertEquals(idEmpleado, response.getEmpleado().getNombre());
        assertEquals(sku, response.getInventario().getSku());
        assertEquals(cantidad, response.getPoliza().getCantidad());

        verify(empleadoRepository, times(1)).findById(idEmpleado);
        verify(inventarioRepository, times(1)).findBySku(sku);
        verify(polizaRepository, times(1)).save(any(Poliza.class));
    }

    @Test
    void testCrearPolizaFalloStockInsuficiente() {
        Long idEmpleado = 1L;
        String sku = "SKU123";
        int cantidad = 15;

        Empleado empleado = new Empleado(idEmpleado, "Juan", "Perez", "Gerente", LocalDateTime.now());
        Inventario inventario = new Inventario(sku, "Producto A", 10, LocalDateTime.now());

        when(empleadoRepository.findById(idEmpleado)).thenReturn(Optional.of(empleado));
        when(inventarioRepository.findBySku(sku)).thenReturn(Optional.of(inventario));

        assertThrows(IllegalStateException.class, () -> polizaService.crearPoliza(idEmpleado, sku, cantidad));
    }

    @Test
    void testEliminarPolizaExito() {
        Long idPoliza = 1L;
        Poliza poliza = new Poliza(idPoliza, new Empleado(), new Inventario(), 5, true, LocalDateTime.now(), LocalDateTime.now());

        when(polizaRepository.findById(idPoliza)).thenReturn(Optional.of(poliza));

        polizaService.eliminarPoliza(idPoliza);

        assertFalse(poliza.isActivo());
        verify(polizaRepository, times(1)).save(poliza);
    }

    @Test
    void testEliminarPolizaNoEncontrada() {
        Long idPoliza = 99L;
        when(polizaRepository.findById(idPoliza)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> polizaService.eliminarPoliza(idPoliza));
    }

    @Test
    void testActualizarEmpleadoEnPolizaExito() {
        Long idPoliza = 1L;
        Long idEmpleado = 2L;

        Empleado nuevoEmpleado = new Empleado(idEmpleado, "Carlos", "Lopez", "Supervisor", LocalDateTime.now());
        Poliza poliza = new Poliza(idPoliza, new Empleado(), new Inventario(), 5, true, LocalDateTime.now(), LocalDateTime.now());

        when(polizaRepository.findById(idPoliza)).thenReturn(Optional.of(poliza));
        when(empleadoRepository.findById(idEmpleado)).thenReturn(Optional.of(nuevoEmpleado));

        polizaService.actualizarEmpleadoEnPoliza(idPoliza, idEmpleado);

        assertEquals(nuevoEmpleado.getId(), poliza.getEmpleadoGenero().getId());
        verify(polizaRepository, times(1)).save(poliza);
    }

    @Test
    void testActualizarEmpleadoEnPolizaNoEncontrada() {
        Long idPoliza = 99L;
        Long idEmpleado = 2L;

        when(polizaRepository.findById(idPoliza)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> polizaService.actualizarEmpleadoEnPoliza(idPoliza, idEmpleado));
    }
}
