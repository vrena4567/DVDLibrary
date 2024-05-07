package org.example.dao;

import org.example.dto.DVD;

import java.util.List;

public interface Dao {
    DVD addDVD(String DVDid, DVD dvd);
    DVD removeDVD(String DVDid);
    DVD updateDVD(String DVDid, DVD dvd);
    List<DVD> getAllDVDs();
    DVD getDvdById(String DVDid);
    DVD getDvdByTitle(String title);

}
