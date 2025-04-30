package com.pixelo.pixelo.ExceptionHandler;

public class pixeloException extends Exception {
    public pixeloException(String message) {
        super(message);
    }
    public pixeloException(String message, Throwable cause) {
        super(message, cause);
    }

}
