package com.coppel.crud_polizas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Schema(name = "ApiResponseDTO", description = "DTO para la respuesta genérica de la API")
public class ApiResponseDTO<T> {
    @Schema(description = "Mensaje del resultado de la petición", example = "Póliza obtenida con éxito")
    private String message;

    @Schema(description = "Representa el cuerpo de la respuesta de la petición")
    private T data;

    @Schema(description = "Devuelve el código numérico de la respuesta de la petición", example = "200")
    private Long codeHttp;

    @Schema(description = "Devuelve la descripción del código de respuesta de la petición", example = "OK")
    private String descriptionHttp;
    @Schema(description = "Devuelve la descripción o lista de mensaje de errores")
    private List<String> errors;

    @Schema(description = "Devuelve la fecha y hora de la petición", example = "2023-06-15T12:00:00.123Z")
    private LocalDateTime timestamp;
}
