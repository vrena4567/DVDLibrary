package org.example.dao;

import org.example.dto.DVD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoFileImplTest {
    DaoFileImpl testDao;
    public DaoFileImplTest(){}

    @BeforeEach
    void setUp() throws IOException {
        String testFile = "testDVDlibrary.txt";
        new FileWriter(testFile);
        testDao = new DaoFileImpl(testFile);
    }

    @Test
    void testAddGetDVD() throws Exception{
        // Create our method test inputs
        String dvdId = "001";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("A Beautiful Mind");
        dvd.setReleaseDate("2002-02-28");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Ron Howard");
        dvd.setStudio("Universal Pictures");
        dvd.setUserNote("A good movie, recommending");

        // Add DVD to the dao
        testDao.addDVD(dvd);

        // Get DVD by id from the dao
        DVD retrievedDVDById = testDao.getDvdById(dvdId);

        // Get DVD by title from dao
        DVD retrievedDVDByTitle = testDao.getDvdByTitle(dvd.getTitle());

        // Check if data is equal: both getting by id and getting by title
        assertEquals(dvd.getDvdId(),
                retrievedDVDById.getDvdId(),
                "Checking dvd id by id.");
        assertEquals(dvd.getDvdId(),
                retrievedDVDByTitle.getDvdId(),
                "Checking dvd id by title.");

        assertEquals(dvd.getTitle(),
                retrievedDVDById.getTitle(),
                "Checking dvd title by id.");
        assertEquals(dvd.getTitle(),
                retrievedDVDByTitle.getTitle(),
                "Checking dvd title by title.");

        assertEquals(dvd.getReleaseDate(),
                retrievedDVDById.getReleaseDate(),
                "Checking dvd release date by id.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDVDByTitle.getReleaseDate(),
                "Checking dvd release date by title.");
    }

    @Test
    void removeDVD() throws Exception {
        // Create the first DVD
        String firstDvdId = "001";
        DVD firstDvd = new DVD(firstDvdId);
        firstDvd.setTitle("A Beautiful Mind");
        firstDvd.setReleaseDate("2002-02-28");
        firstDvd.setMpaaRating("PG-13");
        firstDvd.setDirectorName("Ron Howard");
        firstDvd.setStudio("Universal Pictures");
        firstDvd.setUserNote("A good movie, recommending");

        // Create the second DVD
        String secondDvdId = "002";
        DVD secondDvd = new DVD(secondDvdId);
        secondDvd.setTitle("Dead Poets Society");
        secondDvd.setReleaseDate("1990-03-15");
        secondDvd.setMpaaRating("PG");
        secondDvd.setDirectorName("Peter Weir");
        secondDvd.setStudio("Touchstone Pictures");
        secondDvd.setUserNote("An inspiring movie to watch");

        // Add both DVDs to the dao
        testDao.addDVD(firstDvd);
        testDao.addDVD(secondDvd);

        // Remove the first DVD - 001
        DVD removedDVD = testDao.removeDVD(firstDvdId);

        // Check that the correct object was removed
        assertEquals(removedDVD, firstDvd, "The removed DVD should be 001.");

        // Get all DVD
        List<DVD> allDVD = testDao.getAllDVDs();

        // Check the general content of the list
        assertNotNull(allDVD, "All DVDs list should not be null.");
        assertEquals(1, allDVD.size(), "All DVDs should have only 1 DVD.");

        // Check the specifics
        assertFalse(allDVD.contains(firstDvd), "All DVDS should NOT include 001 DVD.");
        assertTrue(allDVD.contains(secondDvd), "All DVDs should include 002 DVD.");

        // Remove the second DVD - 002
        removedDVD = testDao.removeDVD(secondDvdId);
        // Check that the correct object was removed.
        assertEquals(removedDVD, secondDvd, "The removed DVD should be 002.");

        // Retrieve all the DVD again and check the list
        allDVD = testDao.getAllDVDs();

        // Check the contents of the list - it should be empty
        assertTrue(allDVD.isEmpty(), "The retrieved list of DVDs should be empty.");

        // Try to 'get' both DVDs by their old id - they should be null!
        DVD retrievedDVD = testDao.getDvdById(firstDvd.getDvdId());
        assertNull(retrievedDVD, "001 was removed, should be null.");

        retrievedDVD = testDao.getDvdById(secondDvd.getDvdId());
        assertNull(retrievedDVD, "002 was removed, should be null.");
    }

    @Test
    void updateDVD() throws Exception{
        // Create our method test inputs
        String dvdId = "001";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("A Beautiful Minc");
        dvd.setReleaseDate("2002-02-28");
        dvd.setMpaaRating("PG-13");
        dvd.setDirectorName("Ron Howard");
        dvd.setStudio("Universal Pictures");
        dvd.setUserNote("A good movie, recommending");

        // Add DVD to the dao
        testDao.addDVD(dvd);

        // Update DVD element
        int fieldToEdit = 1;
        String dvdNewInfo = "A Beautiful Mind";
        testDao.updateDVD(dvd, fieldToEdit, dvdNewInfo);

        DVD retrievedDVD = testDao.getDvdById(dvdId);

        // Check if the field been updated correctly
        assertEquals(dvd.getTitle(), dvdNewInfo, "The updated title of the DVD should be: 'A Beautiful Mind'");
        assertEquals(dvd, retrievedDVD, "The retrieved DVD should be equal to the created one.");
    }

    @Test
    void testAddGetAllDVDs() throws Exception{
        // Create the first DVD
        String firstDvdId = "001";
        DVD firstDvd = new DVD(firstDvdId);
        firstDvd.setTitle("A Beautiful Mind");
        firstDvd.setReleaseDate("2002-02-28");
        firstDvd.setMpaaRating("PG-13");
        firstDvd.setDirectorName("Ron Howard");
        firstDvd.setStudio("Universal Pictures");
        firstDvd.setUserNote("A good movie, recommending");

        // Create the second DVD
        String secondDvdId = "002";
        DVD secondDvd = new DVD(secondDvdId);
        secondDvd.setTitle("Dead Poets Society");
        secondDvd.setReleaseDate("1990-03-15");
        secondDvd.setMpaaRating("PG");
        secondDvd.setDirectorName("Peter Weir");
        secondDvd.setStudio("Touchstone Pictures");
        secondDvd.setUserNote("An inspiring movie to watch");

        // Add both DVDs to the dao
        testDao.addDVD(firstDvd);
        testDao.addDVD(secondDvd);

        // Retrieve the list of all DVDs within the dao
        List<DVD> retrievedList = testDao.getAllDVDs();

        // Check the general contents of the list
        assertNotNull(retrievedList, "The retrieved list must not be null.");
        assertEquals(2, retrievedList.size(), "The retrieved list's size should be 2.");

        // Check for the specifics
        assertTrue(testDao.getAllDVDs().contains(firstDvd),
                "The retrieved list shoudl include 'A Beautiful Mind' DVD ");
        assertTrue(testDao.getAllDVDs().contains(secondDvd),
                "The retrieved list shoudl include 'Dead Poets Society' DVD");
    }
}