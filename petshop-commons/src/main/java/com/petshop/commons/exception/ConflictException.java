package com.petshop.commons.exception;

/**
 * Indica um conflito de estado, como uma tentativa de criar
 * um recurso que já existe (ex: CPF duplicado).
 * Resulta em HTTP 409 Conflict.
 */
public class ConflictException extends BusinessException {

    public ConflictException(String message) {
        super(message);
    }
}