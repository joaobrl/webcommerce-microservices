package com.petshop.commons.exception;

/**
 * Indica que um recurso solicitado não foi encontrado.
 * Resulta em HTTP 404 Not Found.
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, Object identifier) {
        super(resource + " not found with identifier: " + identifier);
    }
}