package com.coppel.crud_polizas.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PolizaResponseDTO {
    private PolizaResDTO poliza;
    private EmpleadoPolizaResDTO empleado;
    private InventarioPolizaResDTO inventario;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PolizaResDTO {
        private Long idPoliza;
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        private int cantidad;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class EmpleadoPolizaResDTO {
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
        private String nombre;
        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
        private String apellido;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class InventarioPolizaResDTO {
        @NotBlank(message = "El SKU no puede estar vacío")
        @Size(max = 50, message = "El SKU no puede tener más de 50 caracteres")
        private String sku;

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
        private String nombre;
    }

}

