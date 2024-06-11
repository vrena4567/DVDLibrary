package org.example;

import org.example.controller.Controller;
import org.example.dao.DVDLibraryPersistenceException;
import org.example.dao.Dao;
import org.example.dao.DaoFileImpl;
import org.example.service.DVDService;
import org.example.service.DVDServiceImpl;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;
import org.example.ui.View;

public class App {
    public static void main(String[] args) throws DVDLibraryPersistenceException {
        UserIO userIO = new UserIOConsoleImpl();
        View view = new View(userIO);
        Dao dao = new DaoFileImpl();
        DVDService service = new DVDServiceImpl(dao);
        Controller controller = new Controller(view, service);
        controller.run();
    }
}