package org.example.dao;

import org.example.dto.DVD;

import java.io.*;
import java.util.*;

public class DaoFileImpl implements Dao {
    Map<String, DVD> dvdCollection = new HashMap<>();
    private final String DVD_LIBRARY;
    private final static String DELIMITER = "::";

    public DaoFileImpl() {
        this.DVD_LIBRARY = "dvdlibrary.txt";
    }

    @Override
    public DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        DVD newDVD = dvdCollection.put(dvdId, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        DVD removedDVD = dvdCollection.remove(dvdId);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public DVD updateDVD(DVD dvd, int dvdField, String newInfo) throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        DVD dvdToEdit = dvdCollection.get(dvd.getDvdId());

        switch (dvdField){
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
        writeDVDLibrary();
        return dvdToEdit;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        return new ArrayList<>(dvdCollection.values());
    }

    @Override
    public DVD getDvdById(String dvdId) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        return dvdCollection.get(dvdId);
    }

    @Override
    public DVD getDvdByTitle(String title) throws DVDLibraryPersistenceException{
        loadDVDLibrary();
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDvd : dvdList) {
            if(currentDvd.getTitle().equals(title)){
                return currentDvd;
            }

        }
        return null;
    }

    private void loadDVDLibrary() throws DVDLibraryPersistenceException{
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("Could not load DVD data into memory.", e);
        }

        String currentLine;
        DVD currentDVD;

        while (sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdCollection.put(currentDVD.getDvdId(), currentDVD);
        }

        sc.close();
    }

    private DVD unmarshallDVD(String line){
        String[] splittedLine = line.split(DELIMITER);
        String tokenId = splittedLine[0];
        String tokenTitle = splittedLine[1];
        String tokenReleaseDate = splittedLine[2];
        String tokenMpaaRating = splittedLine[3];
        String tokenDirectorName = splittedLine[4];
        String tokenStudio = splittedLine[5];
        String tokenUserNote = splittedLine[6];

        DVD unmarshalledDVD = new DVD(tokenId);
        unmarshalledDVD.setTitle(tokenTitle);
        unmarshalledDVD.setReleaseDate(tokenReleaseDate);
        unmarshalledDVD.setMpaaRating(tokenMpaaRating);
        unmarshalledDVD.setDirectorName(tokenDirectorName);
        unmarshalledDVD.setStudio(tokenStudio);
        unmarshalledDVD.setUserNote(tokenUserNote);
        return unmarshalledDVD;
    }

    private void writeDVDLibrary() throws DVDLibraryPersistenceException{
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e){
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

    private String marshallDVD(DVD dvd){
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
