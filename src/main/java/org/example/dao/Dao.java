package org.example.dao;

import org.example.dto.DVD;

import java.util.List;

public interface Dao {
    DVD addDVD(String DVDid, DVD dvd) throws DVDLibraryPersistenceException;

    DVD removeDVD(String DVDid) throws DVDLibraryPersistenceException;

    DVD updateDVD(DVD dvd, int dvdField, String newInfo) throws DVDLibraryPersistenceException;

    List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;

    DVD getDvdById(String DVDid) throws DVDLibraryPersistenceException;

    DVD getDvdByTitle(String title) throws DVDLibraryPersistenceException;

}
