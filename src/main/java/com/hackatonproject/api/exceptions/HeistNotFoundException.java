package com.hackatonproject.api.exceptions;

public class HeistNotFoundException extends RuntimeException {

    public HeistNotFoundException() {
    }

    public HeistNotFoundException(String message) {
        super(message);
    }

    public HeistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeistNotFoundException(Throwable cause) {
        super(cause);
    }

    public HeistNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
