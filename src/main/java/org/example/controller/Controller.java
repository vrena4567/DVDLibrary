package org.example.controller;

import org.example.dao.Dao;
import org.example.dao.DaoFileImpl;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;
import org.example.ui.View;

public class Controller {
    private Dao dao = new DaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    private View view = new View(io);
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
            menuSelection = getMenuSelection();
            switch(menuSelection){
                case 1 -> create();
                case 2 -> delete();
                case 3 -> edit();
                case 4 -> listAll();
                case 5 -> searchById();
                case 6 -> searchByTitle();
                case 7 -> exit(); keepGoing = false;
                default -> unknownCommand();

            }
        }

    }

    private int getMenuSelection(){
        view.displayMenu();
        return view.getMenuSelection();
    }

    private void create(){
        // TODO implement create new DVD method
    }

    private void exit(){
        view.displayExitMessage();
    }

    private void unknownCommand(){
        view.displayUnknownCommand();
    }
}
