package com.petshop.commons.exception;

/**
 * Exceção base para todos os erros de negócio da aplicação.
 * Não deve ser instanciada diretamente - use suas subclasses
 * ou crie exceções específicas do seu domínio.
 */
public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }

    protected BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}