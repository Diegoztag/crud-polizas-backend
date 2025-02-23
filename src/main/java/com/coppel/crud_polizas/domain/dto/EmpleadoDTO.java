package com.coppel.crud_polizas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "EmpleadoDTO", description = "DTO para la entidad Empleado")
public class EmpleadoDTO {
    private Long id;

    @Schema(description = "Nombre del empleado", example = "Diego")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Schema(description = "Apellido del empleado", example = "Zazueta")
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private String apellido;

    @Schema(description = "Puesto del empleado", example = "Programador")
    @NotBlank(message = "El puesto no puede estar vacío")
    @Size(max = 50, message = "El puesto no puede tener más de 50 caracteres")
    private String puesto;
}

