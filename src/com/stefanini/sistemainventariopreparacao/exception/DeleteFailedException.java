package com.stefanini.sistemainventariopreparacao.exception;

public class DeleteFailedException extends RuntimeException {
    public DeleteFailedException(String message) {
        super(message);
    }
}
