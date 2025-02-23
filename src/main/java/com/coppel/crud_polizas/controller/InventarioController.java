package com.coppel.crud_polizas.controller;

import com.coppel.crud_polizas.domain.dto.ApiResponseDTO;
import com.coppel.crud_polizas.domain.dto.InventarioDTO;
import com.coppel.crud_polizas.service.InventarioService;
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
@RequestMapping("/inventario")
@Tag(name = "Inventario", description = "Ruta para gestionar el inventario")
public class InventarioController {
    @Autowired
    public InventarioService inventarioService;

    @Autowired
    public ApiResponseBuilder apiResponseBuilder;

    @GetMapping
    @Operation(
            summary = "Obtener todos los artículos del inventario",
            description = "Devuelve una lista de los artículos del inventario"
    )
    public ResponseEntity<ApiResponseDTO<List<InventarioDTO>>> obtenerArticulos() {
        return apiResponseBuilder.success(
                inventarioService.obtenerArticulos(),
                "Artículos obtenidas con éxito");
    }

    @GetMapping("/{sku}")
    @Operation(
            summary = "Obtener un articulo por sku",
            description = "Busca un articulo en el inventario por su sku"
    )
    public ResponseEntity<ApiResponseDTO<InventarioDTO>> obtenerArticuloPorSku(@PathVariable String sku) {
        return apiResponseBuilder.success(
                inventarioService.obtenerArticuloPorSku(sku),
                "Artículo obtenido con éxito");
    }
}
