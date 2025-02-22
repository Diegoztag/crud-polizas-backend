package com.coppel.crud_polizas.exceptions;

// Para recursos duplicados
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
