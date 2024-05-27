package org.example;

import org.example.controller.Controller;
import org.example.dao.DVDLibraryPersistenceException;

public class App {
    public static void main(String[] args) throws DVDLibraryPersistenceException {
        Controller controller = new Controller();
        controller.run();
    }
}