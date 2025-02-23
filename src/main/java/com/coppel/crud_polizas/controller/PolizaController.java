package com.coppel.crud_polizas.controller;

import com.coppel.crud_polizas.domain.dto.ApiResponseDTO;
import com.coppel.crud_polizas.domain.dto.PolizaResponseDTO;
import com.coppel.crud_polizas.service.PolizaService;
import com.coppel.crud_polizas.utils.ApiResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
@Tag(name = "Polizas", description = "Ruta para gestionar pólizas")
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @Autowired
    public ApiResponseBuilder apiResponseBuilder;

    @GetMapping
    @Operation(
            summary = "Obtener todas las pólizas activas",
            description = "Devuelve una lista de pólizas activas en el sistema"
    )
    public ResponseEntity<ApiResponseDTO<List<PolizaResponseDTO>>> obtenerPolizas() {
        return apiResponseBuilder.success(
                polizaService.obtenerPolizas(),
                "Pólizas obtenidas con éxito");
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una póliza por ID",
            description = "Busca una póliza en el sistema por su identificador único"
    )
    public ResponseEntity<ApiResponseDTO<PolizaResponseDTO>> obtenerPolizaPorId(@PathVariable Long id) {
        return apiResponseBuilder.success(
                polizaService.obtenerPolizaPorId(id),
                "Póliza obtenida con éxito");
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva póliza",
            description = "Crea una nueva póliza vinculada a un empleado y un producto del inventario"
    )
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
    @Operation(
            summary = "Elimina lógicamente una póliza",
            description = "Elimina una póliza determinada de forma lógica poniendo su activo en false"
    )
    public ResponseEntity<ApiResponseDTO<Void>> eliminarPoliza(
            @PathVariable @Min(value = 1, message = "El id debe ser mayor a 0") Long id) {
        polizaService.eliminarPoliza(id);
        return apiResponseBuilder.success(
                null,
                "Póliza eliminada con éxito");
    }

    @PutMapping("/{id}/empleado")
    @Operation(
            summary = "Actualiza empleado de una póliza",
            description = "Actualiza el empleado vinculado de una póliza determinada por su id"
    )
    public ResponseEntity<ApiResponseDTO<Void>> actualizarEmpleadoEnPoliza(
            @PathVariable Long id,
            @RequestParam Long idEmpleado) {
        polizaService.actualizarEmpleadoEnPoliza(id, idEmpleado);
        return apiResponseBuilder.success(
                null,
                "Empleado actualizado con éxito");
    }
}
