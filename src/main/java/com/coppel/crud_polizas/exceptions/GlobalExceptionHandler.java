package com.coppel.crud_polizas.exceptions;

import com.coppel.crud_polizas.dto.ApiResponseDTO;
import com.coppel.crud_polizas.utils.ApiResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.error("Recurso no encontrado en {}: {}", request.getRequestURI(), ex.getMessage());
        return apiResponseBuilder.notFound(
                ex.getMessage(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleIllegalStateException(IllegalStateException ex, HttpServletRequest request) {
        logger.error("Estado ilegal en {}: {}", request.getRequestURI(), ex.getMessage());
        return apiResponseBuilder.error(
                ex.getMessage(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleDuplicateResource(DuplicateResourceException ex, HttpServletRequest request) {
        logger.error("Recurso duplicado en {}: {}", request.getRequestURI(), ex.getMessage());
        return apiResponseBuilder.duplicate(
                ex.getMessage(),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("Campo '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        logger.error("Error de validación en {}: {}", request.getRequestURI(), errors);
        return apiResponseBuilder.validationError(
                "Error de validación",
                errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
        logger.error("Error interno del servidor en {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        return apiResponseBuilder.error(
                "Error interno del servidor",
                List.of("Ocurrió un error inesperado. Contacte al administrador.")
        );
    }
}
