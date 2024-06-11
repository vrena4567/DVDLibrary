package org.example.service;

public class DuplicateDVDException extends Exception {
    public DuplicateDVDException(String message) {
        super(message);
    }

    public DuplicateDVDException(String message, Throwable cause) {
        super(message, cause);
    }
}
