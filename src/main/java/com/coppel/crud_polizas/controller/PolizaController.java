package com.coppel.crud_polizas.controller;

import com.coppel.crud_polizas.dto.ApiResponseDTO;
import com.coppel.crud_polizas.dto.Poliza.PolizaResponseDTO;
import com.coppel.crud_polizas.service.PolizaService;
import com.coppel.crud_polizas.utils.ApiResponseBuilder;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @Autowired
    public ApiResponseBuilder apiResponseBuilder;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<PolizaResponseDTO>>> obtenerPolizas() {
        return apiResponseBuilder.success(
                polizaService.obtenerPolizas(),
                "Pólizas obtenidas con éxito");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<PolizaResponseDTO>> obtenerPolizaPorId(@PathVariable Long id) {
        return apiResponseBuilder.success(
                polizaService.obtenerPolizaPorId(id),
                "Póliza obtenida con éxito");
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<PolizaResponseDTO>> crearPoliza(
            @RequestParam Long idEmpleado,
            @RequestParam String sku,
            @RequestParam @Min(value = 1, message = "La cantidad debe ser mayor a 0") int cantidad) {
            polizaService.crearPoliza(idEmpleado, sku, cantidad);
            return apiResponseBuilder.success(
                    polizaService.crearPoliza(idEmpleado, sku, cantidad),
                    "Póliza creada con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> eliminarPoliza(
            @PathVariable @Min(value = 1, message = "El id debe ser mayor a 0") Long id) {
        polizaService.eliminarPoliza(id);
        return apiResponseBuilder.success(
                null,
                "Póliza eliminada con éxito");
    }

    @PutMapping("/{id}/empleado")
    public ResponseEntity<ApiResponseDTO<Void>> actualizarEmpleadoEnPoliza(
            @PathVariable Long id,
            @RequestParam Long nuevoEmpleadoId) {
        polizaService.actualizarEmpleadoEnPoliza(id, nuevoEmpleadoId);
        return apiResponseBuilder.success(
                null,
                "Empleado actualizado con éxito");
    }
}
