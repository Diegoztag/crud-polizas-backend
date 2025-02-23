package com.coppel.crud_polizas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "InventarioDTO", description = "DTO para la entidad de Inventario")
public class InventarioDTO {

    @Schema(description = "Sku del artículo", example = "SKU003")
    @NotBlank(message = "El SKU no puede estar vacío")
    @Size(max = 50, message = "El SKU no puede tener más de 50 caracteres")
    private String sku;

    @Schema(description = "Nombre del artículo", example = "Laptop")
    @NotBlank(message = "El nombre del artículo no puede estar vacío")
    @Size(max = 100, message = "El nombre del artículo no puede tener más de 100 caracteres")
    private String nombre;

    @Schema(description = "Cantidad de artículos en el inventario", example = "50")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;
}

