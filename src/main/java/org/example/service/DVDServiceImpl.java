package org.example.service;

import org.example.dao.DVDLibraryPersistenceException;
import org.example.dao.Dao;
import org.example.dto.DVD;

import java.util.List;

public class DVDServiceImpl implements DVDService {
    private Dao dao;

    public DVDServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void createDVD(DVD dvd) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException, DuplicateDVDException {
        if(dao.getDvdById(dvd.getDvdId()) != null) {
            throw new DuplicateDVDException(
                "ERROR: Could not create DVD. DVD id " +
                        dvd.getDvdId() +
                        " already exists."
            );
        }
        // Validate inserted information
        validateDVD(dvd);

        dao.addDVD(dvd);
    }

    @Override
    public DVD removeDVD(String DVDid) {
        return null;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVDById(String DVDid) throws DVDLibraryPersistenceException {
        return dao.getDvdById(DVDid);
    }

    @Override
    public DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException {
        return dao.getDvdByTitle(title);
    }

    private void validateDVD(DVD dvd) throws DVDLibraryDataValidationException{
        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0) {

            throw new DVDLibraryDataValidationException (
                "ERROR: All fields [Title, Release Date, MPAA Rating," +
                        "Director name, Studio] are required.");
        }
    }
}
