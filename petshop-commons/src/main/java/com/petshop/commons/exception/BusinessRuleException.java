package com.petshop.commons.exception;

/**
 * Indica que uma regra de negócio foi violada.
 * Resulta em HTTP 422 Unprocessable Entity.
 * Use quando a requisição está sintaticamente correta,
 * mas semanticamente inválida no contexto do negócio.
 */
public class BusinessRuleException extends BusinessException {

    public BusinessRuleException(String message) {
        super(message);
    }
}