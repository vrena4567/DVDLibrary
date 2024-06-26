package org.example.dao;

import org.example.dto.DVD;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class DaoFileImpl implements Dao {
    Map<String, DVD> dvdCollection = new HashMap<>();
    private final String DVD_LIBRARY;
    private final static String DELIMITER = "::";

    public DaoFileImpl() {
        this.DVD_LIBRARY = "dvdlibrary.txt";
    }

    public DaoFileImpl(String dvdTextFile) {
        this.DVD_LIBRARY = dvdTextFile;
    }

    /**
     * Adds a given DVD to the library and associates it with the given DVD id.
     * If there is already a DVD associated with the given DVD id it will return
     * that DVD object, otherwise it will return null.
     *
     * @param dvd DVD to be added to the library
     * @return the DVD object previously associated with the given DVD id if
     * it exist, null otherwise
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     *      * loading of the file wasn't successful
     */
    @Override
    public DVD addDVD(DVD dvd) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        DVD newDVD = dvdCollection.put(dvd.getDvdId(), dvd);
        writeDVDLibrary();
        return newDVD;
    }

    /**
     * Removes from the library the DVD associated with the given id.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the given id
     *
     * @param dvdId id of DVD to be removed
     * @return DVD object that was removed or null if no DVD was associated
     * with the given DVD id
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     * loading of the file wasn't successful
     */
    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        DVD removedDVD = dvdCollection.remove(dvdId);
        writeDVDLibrary();
        return removedDVD;
    }

    /**
     * Updates the given DVD in the library with the given information
     * based on the given field associated with the DVD field number.
     *
     * @param dvdToEdit DVD to be updated in the library
     * @param dvdField field number to be updated
     * @param newInfo new information to be saved to the given DVD
     * @return updated DVD object
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     * loading of the file wasn't successful
     */

    @Override
    public DVD updateDVD(DVD dvdToEdit, int dvdField, String newInfo) throws DVDLibraryPersistenceException {
        loadDVDLibrary();

        switch (dvdField) {
            case 1:
                dvdToEdit.setTitle(newInfo);
                break;
            case 2:
                dvdToEdit.setReleaseDate(newInfo);
                break;
            case 3:
                dvdToEdit.setMpaaRating(newInfo);
                break;
            case 4:
                dvdToEdit.setDirectorName(newInfo);
                break;
            case 5:
                dvdToEdit.setStudio(newInfo);
                break;
            case 6:
                dvdToEdit.setUserNote(newInfo);
                break;
            default:
                return null;
        }
        dvdCollection.put(dvdToEdit.getDvdId(), dvdToEdit);
        writeDVDLibrary();
        return dvdToEdit;
    }

    /**
     * Returns a List of all DVDs in the library.
     *
     * @return DVD List containing all the DVDS in the library.
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     * loading of the file wasn't successful
     */
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        return new ArrayList<DVD>(dvdCollection.values());
    }

    /**
     * Returns the DVD object associated with the given DVD id.
     * Returns null if no such DVD exists
     *
     * @param dvdId id of the DVD to retrieve
     * @return the DVD object associated with the given DVD id,
     * null if no such DVD exists
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     * loading of the file wasn't successful
     */
    @Override
    public DVD getDvdById(String dvdId) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        return dvdCollection.get(dvdId);
    }

    /**
     * Returns the DVD object associated with the given DVD title.
     * Returns null if no such DVD exists
     *
     * @param title title of the DVD to retrieve
     * @return the DVD object associated with the given DVD title,
     * null if no such DVD exists
     * @throws DVDLibraryPersistenceException exception if the wirtting or
     * loading of the file wasn't successful
     */
    @Override
    public DVD getDvdByTitle(String title) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDvd : dvdList) {
            if (currentDvd.getTitle().equals(title)) {
                return currentDvd;
            }

        }
        return null;
    }

    @Override
    public List<DVD> filterByYear(int releaseYear) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        List<DVD> sameYearDvds = dvdCollection.values().stream()
                .filter(dvd -> dvd.getReleaseDate().getYear() == releaseYear)
                .collect(Collectors.toList());
        return sameYearDvds;
    }

    @Override
    public List<DVD> filterByRating(String mpaaRating) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        List<DVD> filteredByRate = dvdCollection.values().stream()
                .filter(dvd -> dvd.getMpaaRating().equals(mpaaRating))
                .collect(Collectors.toList());
        return filteredByRate;
    }

    @Override
    public List<DVD> filterByDirector(String directorName) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        List<DVD> filteredByDir = dvdCollection.values().stream()
                .filter(dvd -> dvd.getDirectorName().equals(directorName))
                .collect(Collectors.toList());
        return filteredByDir;
    }

    @Override
    public List<DVD> filterByStudio(String studio) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        List<DVD> filteredByStud = dvdCollection.values().stream()
                .filter(dvd -> dvd.getStudio().equals(studio))
                .collect(Collectors.toList());
        return filteredByStud;
    }

    @Override
    public String findAvarageAge() throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        LocalDate today = LocalDate.now();
        DecimalFormat df = new DecimalFormat("0.00");
        double avrAge = dvdCollection.values().stream()
                .collect(Collectors.averagingDouble(dvd -> ChronoUnit.YEARS.between(dvd.getReleaseDate(), today)));
        return df.format(avrAge);
    }

    @Override
    public DVD findNewestMovie() throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        Optional<DVD> newestMovie = dvdCollection.values().stream()
                .max(Comparator.comparing(DVD::getReleaseDate));
        return newestMovie.get();
    }

    @Override
    public DVD findOldestMovie() throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        Optional<DVD> oldestMovie = dvdCollection.values().stream()
                .min(Comparator.comparing(DVD::getReleaseDate));
        return oldestMovie.get();
    }


    private void loadDVDLibrary() throws DVDLibraryPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("Could not load DVD data into memory.", e);
        }

        String currentLine;
        DVD currentDVD;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdCollection.put(currentDVD.getDvdId(), currentDVD);
        }

        sc.close();
    }

    private DVD unmarshallDVD(String line) {
        String[] dvdTokens = line.split(DELIMITER);
        String dvdId = dvdTokens[0];

        DVD unmarshalledDVD = new DVD(dvdId);

        unmarshalledDVD.setTitle(dvdTokens[1]);
        unmarshalledDVD.setReleaseDate(dvdTokens[2]);
        unmarshalledDVD.setMpaaRating(dvdTokens[3]);
        unmarshalledDVD.setDirectorName(dvdTokens[4]);
        unmarshalledDVD.setStudio(dvdTokens[5]);
        unmarshalledDVD.setUserNote(dvdTokens[6]);
        return unmarshalledDVD;
    }

    private void writeDVDLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save DVD to data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private String marshallDVD(DVD dvd) {
        String marshalledDVD = dvd.getDvdId() + DELIMITER;
        marshalledDVD += dvd.getTitle() + DELIMITER;
        marshalledDVD += dvd.getReleaseDate() + DELIMITER;
        marshalledDVD += dvd.getMpaaRating() + DELIMITER;
        marshalledDVD += dvd.getDirectorName() + DELIMITER;
        marshalledDVD += dvd.getStudio() + DELIMITER;
        marshalledDVD += dvd.getUserNote();
        return marshalledDVD;
    }

}
