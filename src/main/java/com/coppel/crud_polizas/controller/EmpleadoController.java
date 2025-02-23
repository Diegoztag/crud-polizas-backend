package com.coppel.crud_polizas.controller;

import com.coppel.crud_polizas.domain.dto.ApiResponseDTO;
import com.coppel.crud_polizas.domain.dto.EmpleadoDTO;
import com.coppel.crud_polizas.domain.dto.InventarioDTO;
import com.coppel.crud_polizas.service.EmpleadoService;
import com.coppel.crud_polizas.utils.ApiResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleados", description = "Ruta para gestionar empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    public ApiResponseBuilder apiResponseBuilder;

    @GetMapping
    @Operation(
            summary = "Obtener todos los empleados",
            description = "Devuelve una lista de los empleados"
    )
    public ResponseEntity<ApiResponseDTO<List<EmpleadoDTO>>> obtenerEmpleados() {
        return apiResponseBuilder.success(
                empleadoService.obtenerEmpleados(),
                "Empleados obtenidas con éxito");
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un empleado por id",
            description = "Busca un empleado en el sistema por su identificador único"
    )
    public ResponseEntity<ApiResponseDTO<EmpleadoDTO>> obtenerEmpleadoPorId(@PathVariable Long id) {
        return apiResponseBuilder.success(
                empleadoService.obtenerEmpleadoPorId(id),
                "Empleado obtenido con éxito");
    }
}
