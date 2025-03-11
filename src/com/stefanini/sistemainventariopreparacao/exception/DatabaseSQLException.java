package com.stefanini.sistemainventariopreparacao.exception;

public class DatabaseSQLException extends RuntimeException {
    public DatabaseSQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
