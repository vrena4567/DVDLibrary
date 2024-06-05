package org.example.controller;

import org.example.dao.DVDLibraryPersistenceException;
import org.example.dao.Dao;
import org.example.dao.DaoFileImpl;
import org.example.dto.DVD;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;
import org.example.ui.View;

import java.util.ArrayList;
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
                case 7 -> filterBy();
                case 8 -> findOne();
                case 9 -> {
                    exit();
                    keepGoing = false;
                }
                default -> unknownCommand();

            }
        }

    }
    private void findOne() throws DVDLibraryPersistenceException {
        int choice = view.displayFinderMenu();
        DVD foundDVD;
        switch (choice) {
            case 1 -> {
                String average = dao.findAvarageAge();
                view.displayAverageAge(average);
            }
            case 2 -> {
                DVD youngest = dao.findNewestMovie();
                view.displayDVD(youngest);
            }
            case 3 -> {
                DVD oldest = dao.findOldestMovie();
                view.displayDVD(oldest);
            }
            default -> unknownCommand();
        }
    }

    private void filterBy() throws DVDLibraryPersistenceException {
        int choice = view.displayFilterMenu();
        List<DVD> filteredList = new ArrayList<>();
        switch (choice) {
            case 1 -> {
                int year = view.getYearInput();
                filteredList = dao.filterByYear(year);
            }
            case 2 -> {
                String MpaaRating = view.getMpaaRating();
                dao.filterByRating(MpaaRating);
            }
            case 3 -> {
                String directorName = view.getDirectorNameInput();
                filteredList = dao.filterByDirector(directorName);
            }
            case 4 -> {
                String studioName = view.getStudioInput();
                filteredList = dao.filterByStudio(studioName);
            }
            default -> unknownCommand();
        }
        view.displayAllDvds(filteredList);

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
