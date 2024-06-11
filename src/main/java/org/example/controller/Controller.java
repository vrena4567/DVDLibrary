package org.example.controller;

import org.example.dao.DVDLibraryPersistenceException;
import org.example.dto.DVD;
import org.example.service.DVDLibraryDataValidationException;
import org.example.service.DVDService;
import org.example.service.DuplicateDVDException;
import org.example.ui.View;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private View view;
    private DVDService service;

    public Controller (View view, DVDService service) {
        this.view = view;
        this.service = service;
    }

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
        switch (choice) {
            case 1 -> {
                String average = service.findAvarageAge();
                view.displayAverageAge(average);
            }
            case 2 -> {
                DVD youngest = service.findNewestMovie();
                view.displayDVD(youngest);
            }
            case 3 -> {
                DVD oldest = service.findOldestMovie();
                view.displayDVD(oldest);
            }
            default -> unknownCommand();
        }
    }

    private void filterBy() throws DVDLibraryPersistenceException {
        // TODO
        int choice = view.displayFilterMenu();
        List<DVD> filteredList = new ArrayList<>();
        switch (choice) {
            case 1 -> {
                int year = view.getYearInput();
                filteredList = service.filterByYear(year);
            }
            case 2 -> {
                String MpaaRating = view.getMpaaRating();
                service.filterByRating(MpaaRating);
            }
            case 3 -> {
                String directorName = view.getDirectorNameInput();
                filteredList = service.filterByDirector(directorName);
            }
            case 4 -> {
                String studioName = view.getStudioInput();
                filteredList = service.filterByStudio(studioName);
            }
            default -> unknownCommand();
        }
        view.displayAllDvds(filteredList);

    }

    private void searchByTitle() throws DVDLibraryPersistenceException{
        view.displaySearchByTitleBanner();
        String title = view.getDVDTitle();
        DVD searchedDvd = service.getDVDByTitle(title);
        view.displayDVD(searchedDvd);
    }

    private void searchById() throws DVDLibraryPersistenceException {
        view.displaySearchByIdBanner();
        String dvdId = view.getDvdIdForSearch();
        DVD searchedDVD = service.getDVDById(dvdId);
        view.displayDVD(searchedDVD);
    }

    private void delete() throws DVDLibraryPersistenceException {
        view.displayDeleteBanner();
        String dvdId = view.deleteDVDById();
        DVD dvd = service.getDVDById(dvdId);
        service.removeDVD(dvdId);
        view.displaySuccessDeletionBanner(dvd);
    }

    private void edit() throws DVDLibraryPersistenceException {
        view.displayEditBanner();
        String dvdId = view.getDvdIdForEdit();
        DVD dvdToEdit = service.getDVDById(dvdId);
        int fieldNum = view.getFieldToEdit();
        String newInfoForField = view.getNewInfoForDVDField();
        //TODO
        service.updateDVD(dvdToEdit, fieldNum, newInfoForField);
        view.displayEditSuccesBanner();
    }

    private int getMenuSelection() {
        view.displayMenu();
        return view.getMenuSelection();
    }

    private void create() throws DVDLibraryPersistenceException {
        boolean hasError;
        do {
            DVD dvd = view.getNewDvdInfo();
            try {
                service.createDVD(dvd);
                view.displaySuccessBannerNewDvd();
                hasError = false;
            } catch (DuplicateDVDException | DVDLibraryDataValidationException e) {
                hasError = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasError);
    }

    private void listAll() throws DVDLibraryPersistenceException {
        view.displayListBanner();
        List <DVD> dvdList = service.getAllDVDs();
        view.displayAllDvds(dvdList);
    }

    private void exit() {
        view.displayExitMessage();
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }
}
