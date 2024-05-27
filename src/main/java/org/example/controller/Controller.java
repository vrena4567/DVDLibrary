package org.example.controller;

import org.example.dao.DVDLibraryPersistenceException;
import org.example.dao.Dao;
import org.example.dao.DaoFileImpl;
import org.example.dto.DVD;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;
import org.example.ui.View;

import java.util.List;

public class Controller {
    private Dao dao = new DaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    private View view = new View(io);

    public void run() throws DVDLibraryPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1 -> create();
                case 2 -> delete();
                case 3 -> edit();
                case 4 -> listAll();
                case 5 -> searchById();
                case 6 -> searchByTitle();
                case 7 -> {
                    exit();
                    keepGoing = false;
                }
                default -> unknownCommand();

            }
        }

    }

    private void searchByTitle() throws DVDLibraryPersistenceException{
        view.displaySearchByTitleBanner();
        String title = view.getDVDTitle();
        DVD searchedDvd = dao.getDvdByTitle(title);
        view.displayDVD(searchedDvd);
    }

    private void searchById() throws DVDLibraryPersistenceException {
        view.displaySearchByIdBanner();
        String dvdId = view.getDvdIdForSearch();
        DVD searchedDVD = dao.getDvdById(dvdId);
        view.displayDVD(searchedDVD);
    }

    private void delete() throws DVDLibraryPersistenceException {
        view.displayDeleteBanner();
        String dvdId = view.deleteDVDById();
        DVD dvd = dao.getDvdById(dvdId);
        dao.removeDVD(dvdId);
        view.displaySuccessDeletionBanner(dvd);
    }

    private void edit() throws DVDLibraryPersistenceException {
        view.displayEditBanner();
        String dvdId = view.getDvdIdForEdit();
        DVD dvdToEdit = dao.getDvdById(dvdId);
        int fieldNum = view.getFieldToEdit();
        String newInfoForField = view.getNewInfoForDVDField();
        dao.updateDVD(dvdToEdit, fieldNum, newInfoForField);
        view.displayEditSuccesBanner();
    }

    private int getMenuSelection() {
        view.displayMenu();
        return view.getMenuSelection();
    }

    private void create() throws DVDLibraryPersistenceException {
        DVD dvd = view.getNewDvdInfo();
        dao.addDVD(dvd.getDvdId(), dvd);
        view.displaySuccessBannerNewDvd();
    }

    private void listAll() throws DVDLibraryPersistenceException {
        view.displayListBanner();
        List <DVD> dvdList = dao.getAllDVDs();
        view.displayAllDvds(dvdList);
    }

    private void exit() {
        view.displayExitMessage();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }
}
