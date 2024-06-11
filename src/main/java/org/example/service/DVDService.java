package org.example.service;

import org.example.dao.DVDLibraryPersistenceException;
import org.example.dto.DVD;

import java.util.List;

public interface DVDService {

    void createDVD(DVD dvd) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException, DuplicateDVDException;
    DVD removeDVD(String DVDid);
    List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;
    DVD getDVDById(String DVDid) throws DVDLibraryPersistenceException;
    DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException;

}
