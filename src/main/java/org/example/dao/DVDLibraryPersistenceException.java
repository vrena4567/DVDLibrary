package org.example.dao;

import java.io.FileNotFoundException;

public class DVDLibraryPersistenceException extends Exception {
    public DVDLibraryPersistenceException(String message) {
        super(message);
    }

    public DVDLibraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
