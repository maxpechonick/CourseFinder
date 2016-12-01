package com.pechonick.registration.pool.exception;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
