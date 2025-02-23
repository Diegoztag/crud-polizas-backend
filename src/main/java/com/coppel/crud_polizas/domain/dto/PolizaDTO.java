package com.coppel.crud_polizas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "PolizaDTO", description = "DTO para la entidad Poliza")
public class PolizaDTO {
    private Long idPoliza;

    @Schema(description = "ID del empleado que genera la póliza", example = "1")
    @NotNull(message = "El ID del empleado no puede ser nulo")
    @Positive(message = "El ID del empleado debe ser un número positivo")
    private Long idEmpleado;

    @Schema(description = "SKU del producto en inventario", example = "ABC123")
    @NotBlank(message = "El SKU del inventario no puede estar vacío")
    private String sku;

    @Schema(description = "Cantidad de artículos en la póliza", example = "10")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int cantidad;
}

