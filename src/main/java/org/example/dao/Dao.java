package org.example.dao;

import org.example.dto.DVD;

import java.util.List;

public interface Dao {
    DVD addDVD(DVD dvd) throws DVDLibraryPersistenceException;

    DVD removeDVD(String DVDid) throws DVDLibraryPersistenceException;

    DVD updateDVD(DVD dvd, int dvdField, String newInfo) throws DVDLibraryPersistenceException;

    List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;

    DVD getDvdById(String DVDid) throws DVDLibraryPersistenceException;

    DVD getDvdByTitle(String title) throws DVDLibraryPersistenceException;

    List<DVD> filterByYear(int releaseYear) throws DVDLibraryPersistenceException;
    List<DVD> filterByRating(String mpaaRating) throws DVDLibraryPersistenceException;
    List<DVD> filterByDirector(String directorName) throws DVDLibraryPersistenceException;
    List<DVD> filterByStudio(String studio) throws DVDLibraryPersistenceException;
    String findAvarageAge() throws DVDLibraryPersistenceException;
    DVD findNewestMovie() throws DVDLibraryPersistenceException;
    DVD findOldestMovie() throws DVDLibraryPersistenceException;
}
