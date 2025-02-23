package com.coppel.crud_polizas.utils;

import com.coppel.crud_polizas.domain.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApiResponseBuilder {

    private <T> ApiResponseDTO<T> buildResponse(T data, String message, HttpStatus status, List<String> errors) {
        return ApiResponseDTO.<T>builder()
                .message(message)
                .data(data)
                .codeHttp((long) status.value())
                .descriptionHttp(status.getReasonPhrase())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
    public <T> ResponseEntity<ApiResponseDTO<T>> success(T data, String message) {
        return ResponseEntity.ok(buildResponse(data, message, HttpStatus.OK, null));
    }

    public <T> ResponseEntity<ApiResponseDTO<T>> notFound(String message, List<String> errors) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(null, message, HttpStatus.NOT_FOUND, errors));
    }

    public <T> ResponseEntity<ApiResponseDTO<T>> error(String message, List<String> errors) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    public ResponseEntity<ApiResponseDTO<Void>> duplicate(String message, List<String> errors) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildResponse(null, message, HttpStatus.CONFLICT, errors));
    }

    public ResponseEntity<ApiResponseDTO<Void>> validationError(String message, List<String> errors) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponse(null, message, HttpStatus.BAD_REQUEST, errors));
    }

}
